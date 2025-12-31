package com.example.assignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    DBHelper helper;
    List<Restaurant> restaurants;
    List<Food> bucket;
    List<Food> prevOrders;

    RecyclerView recyclerView;
    TextView title, total;
    Button button1;
    Button button2;

    String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new DBHelper(getApplicationContext());
        restaurants = helper.getDBAsList();
        System.out.println(restaurants.size());
        bucket = new ArrayList<Food>();
        prevOrders = new ArrayList<Food>();

        recyclerView = findViewById(R.id.recyclerView);
        title = findViewById(R.id.title);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        total = findViewById(R.id.total);

        restaurantView();

        login = null;
    }

    private void restaurantView ()
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new RestaurantAdapter(restaurants, MainActivity.this));
        title.setText("RESTAURANTS LIST");
        button1.setText("VIEW BUCKET");
        button1.setVisibility(View.VISIBLE);
        button2.setText("");
        button2.setVisibility(View.GONE);
        button1.setOnClickListener(v -> bucketView());
        total.setText("");
    }

    private void bucketView ()
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new FoodAdapter(bucket,MainActivity.this, 0));
        title.setText("BUCKET");
        button1.setText("GO BACK");
        button1.setVisibility(View.VISIBLE);
        button2.setText("CHECKOUT");
        button2.setVisibility(View.VISIBLE);
        button1.setOnClickListener(v -> restaurantView());

        if (login == null)
            button2.setOnClickListener(v -> login());
        else
            button2.setOnClickListener(v -> checkoutSuccess());
        total.setText("Your total is: $" + calculateTotal() + ".");
    }

    private void foodView(@NonNull Restaurant restaurant)
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new FoodAdapter(restaurant.getFoodList(),MainActivity.this, 1));
        title.setText(restaurant.getName().toUpperCase() + " MENU");
        button1.setText("GO BACK");
        button1.setVisibility(View.VISIBLE);
        button2.setText("BUCKET");
        button2.setVisibility(View.VISIBLE);
        button1.setOnClickListener(v -> restaurantView());
        button2.setOnClickListener(v -> bucketView());
        total.setText("");
    }

    private void login()
    {
        setContentView(R.layout.activity_login);
        TextView email, pass;
        Button logreg, back;

        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        logreg = findViewById(R.id.logreg);
        back = findViewById(R.id.back);

        logreg.setOnClickListener(v ->
        {
            if (email.getText().toString().length() == 0)
                Toast.makeText(getApplicationContext(), "Please enter your email!", Toast.LENGTH_LONG).show();
            else if (helper.getReadableDatabase().rawQuery("SELECT * FROM Users WHERE email = '" + email.getText().toString() + "';", null).getCount() <= 0)
            {
                if (pass.getText().toString().length() != 0)
                {
                    helper.addUser(email.getText().toString(), pass.getText().toString());
                    Toast.makeText(getApplicationContext(), "Registered successfully!", Toast.LENGTH_LONG).show();
                    login = email.getText().toString();
                    checkoutSuccess();
                }
                else
                    Toast.makeText(getApplicationContext(), "Please enter a password!", Toast.LENGTH_LONG).show();
            }
            else
            {
                if (helper.getReadableDatabase().rawQuery("SELECT * FROM Users WHERE email = '" + email.getText().toString() + "'" + " AND pass = '" + pass.getText().toString() + "';", null).getCount() <= 0)
                    Toast.makeText(getApplicationContext(), "Please enter the correct password!", Toast.LENGTH_LONG).show();
                else
                {
                    Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_LONG).show();
                    login = email.getText().toString();
                    checkoutSuccess();
                }
            }
        });

        back.setOnClickListener(v -> bucketView());
    }

    private void checkoutSuccess ()
    {
        helper.addOrder(login, bucket);
        setContentView(R.layout.activity_login);
        Button exit = findViewById(R.id.logreg), past = findViewById(R.id.back);
        TextView checkTitle = findViewById(R.id.checkTitle);

        findViewById(R.id.email).setVisibility(View.GONE);
        findViewById(R.id.pass).setVisibility(View.GONE);
        findViewById(R.id.emailText).setVisibility(View.GONE);
        findViewById(R.id.passText).setVisibility(View.GONE);

        checkTitle.setText("CHECKOUT SUCCESS");
        past.setText("VIEW PREVIOUS ORDERS");

        exit.setText("CLOSE");

        exit.setOnClickListener(v -> finishAndRemoveTask());
        past.setOnClickListener(v -> past());
    }

    private void past ()
    {
        if (login.length() == 0)
            Toast.makeText(getApplicationContext(), "Please Login First!", Toast.LENGTH_LONG).show();
        else
        {
            Cursor cursor = helper.getReadableDatabase().rawQuery("SELECT restaurant, name, image, price, date FROM Orders WHERE user = '" + login + "';", null);

            if (cursor.moveToFirst())
            {
                do
                {
                    String restaurant, name, image, date, price;

                    Cursor forRestName = helper.getReadableDatabase().rawQuery("SELECT name FROM Restaurants WHERE id = '" + cursor.getString(0) + "';", null);
                    forRestName.moveToFirst();
                    restaurant = forRestName.getString(0);
                    forRestName.close();
                    name = cursor.getString(1);
                    image = cursor.getString(2);
                    price = cursor.getString(3);
                    date = cursor.getString(4);

                    prevOrders.add(new Food(restaurant, name, image, price, date));
                } while (cursor.moveToNext());
            }

            cursor.close();

            setContentView(R.layout.activity_main);
            recyclerView = findViewById(R.id.recyclerView);
            title = findViewById(R.id.title);
            button1 = findViewById(R.id.button1);
            button2 = findViewById(R.id.button2);
            total = findViewById(R.id.total);

            title.setText("YOUR PREVIOUS ORDERS");
            button1.setVisibility(View.VISIBLE);
            button1.setText("BACK");
            button2.setVisibility(View.GONE);
            total.setVisibility(View.GONE);
            button1.setOnClickListener(v -> checkoutSuccess());
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerView.setAdapter(new FoodAdapter(prevOrders,MainActivity.this, 2));
        }
    }

    private double calculateTotal ()
    {
        Iterator<Food> iterator = bucket.iterator();
        double total = 0.0;
        while (iterator.hasNext())  total += iterator.next().getPrice();
        return total;
    }

    private class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.MyViewHolder>
    {
        List<Restaurant> restaurantList;
        Context context;

        public RestaurantAdapter (List<Restaurant> restaurantList, Context context)
        {
            this.restaurantList = restaurantList;
            this.context = context;
        }

        @NonNull
        @Override
        public RestaurantAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)  {return new RestaurantAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_layout, parent, false));}

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.name.setText(restaurantList.get(position).getName());
            holder.picture.setImageResource(R.drawable.food);
            holder.button.setText("VIEW FOOD");
            holder.button.setOnClickListener(v -> foodView(restaurantList.get(holder.getAdapterPosition())));
        }

        @Override
        public int getItemCount() {return restaurantList.size();}

        public class MyViewHolder extends RecyclerView.ViewHolder
        {
            ImageView picture;
            TextView name;
            Button button;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                picture = itemView.findViewById(R.id.picture);
                name = itemView.findViewById(R.id.name);
                button = itemView.findViewById(R.id.button);
            }
        }
    }

    private class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder>
    {
        List<Food> foodList;
        Context context;
        int type;   //0 is bucket, 1 is food and 2 is past

        public FoodAdapter (List<Food> foodList, Context context, int type)
        {
            this.foodList = foodList;
            this.context = context;
            this.type = type;
        }

        @NonNull
        @Override
        public FoodAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)  {return new FoodAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_layout, parent, false));}
        @Override
        public void onBindViewHolder(@NonNull FoodAdapter.MyViewHolder holder, int position) {
            holder.name.setText(foodList.get(position).getName());
            holder.price.setText("$ " + Double.toString(foodList.get(position).getPrice()));
            holder.desc.setText(foodList.get(position).getDesc());
            holder.picture.setImageResource(R.drawable.food);

            if (type == 0)
            {
                holder.button.setText("REMOVE FROM BUCKET");
                holder.button.setOnClickListener(v -> {
                    bucket.remove(position);
                    notifyDataSetChanged();
                });
            }
            else if (type == 1)
            {
                holder.button.setText("ADD TO BUCKET");
                holder.button.setOnClickListener(v -> bucket.add(foodList.get(position).copy()));
            }
            else
                holder.button.setVisibility(View.GONE);
        }

        @Override
        public int getItemCount() {return foodList.size();}

        public class MyViewHolder extends RecyclerView.ViewHolder
        {
            ImageView picture;
            TextView name, price, desc;
            Button button;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                picture = itemView.findViewById(R.id.picture);
                name = itemView.findViewById(R.id.name);
                button = itemView.findViewById(R.id.button);
                price = itemView.findViewById(R.id.price);
                desc = itemView.findViewById(R.id.description);
            }
        }
    }
}