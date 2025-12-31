package com.example.assignment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "FoodDelivery";
    private static final int DB_VERSION = 1;
    private static final String TABLE1_NAME = "Restaurants", TABLE1_ID = "id", TABLE1_RESTAURANT_NAME = "name", TABLE1_IMAGE = "image";
    private static final String TABLE2_NAME = "Food", TABLE2_RESTAURANT_ID = "restaurant", TABLE2_FOOD_NAME = "name", TABLE2_IMAGE = "image", TABLE2_PRICE = "price", TABLE2_DESCRIPTION = "description";
    private static final String TABLE3_NAME = "Users", TABLE3_EMAIL = "email", TABLE3_PASS = "pass";
    private static final String TABLE4_NAME = "Orders", TABLE4_USER = "user", TABLE4_RESTAURANT_ID = "restaurant", TABLE4_FOOD = "name", TABLE4_IMAGE = "image", TABLE4_PRICE = "price", TABLE4_DATE = "date";

    //NAME: DBHelper
    //PURPOSE: Constructor for DBHelper.
    //INPUTS: Context context - application context
    //OUTPUTS: NONE
    public DBHelper(@Nullable Context context) {super(context, DB_NAME, null, DB_VERSION);}

    //NAME: onCreate
    //PURPOSE: Function to be executed when the database is created.
    //INPUTS: SQLiteDatabase db - the database
    //OUTPUTS: NONE
    @Override
    public void onCreate(SQLiteDatabase db)
    {
    }

    //NAME: latest
    //PURPOSE: Function which updates all the application with all the latest information.
    //INPUTS: NONE
    //OUTPUTS: NONE
    public void latest()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE2_NAME);

        //CREATE TABLE IF NOT EXISTS Restaurants (id TEXT PRIMARY KEY, name TEXT, image TEXT);
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE1_NAME + " ("
                + TABLE1_ID + " TEXT PRIMARY KEY, "
                + TABLE1_RESTAURANT_NAME + " TEXT, "
                + TABLE1_IMAGE + " TEXT);";
        db.execSQL(query);

        //CREATE TABLE IF NOT EXISTS Food (restaurant TEXT, name TEXT, image TEXT, price FLOAT, description TEXT, PRIMARY KEY (restaurant, name), FOREIGN KEY (restaurant) REFERENCES Restaurants(id)
        query = "CREATE TABLE IF NOT EXISTS " + TABLE2_NAME + " ("
                + TABLE2_RESTAURANT_ID + " TEXT, "
                + TABLE2_FOOD_NAME + " TEXT, "
                + TABLE2_IMAGE + " TEXT, "
                + TABLE2_PRICE + " FLOAT, "
                + TABLE2_DESCRIPTION + " TEXT, "
                + "PRIMARY KEY (" + TABLE2_RESTAURANT_ID + ", " + TABLE2_FOOD_NAME + "), "
                + "FOREIGN KEY (" + TABLE2_RESTAURANT_ID + ") REFERENCES " + TABLE1_NAME + "(" + TABLE1_ID + "));";
        db.execSQL(query);

        //CREATE TABLE IF NOT EXISTS Users (email TEXT, pass TEXT);
        query = "CREATE TABLE IF NOT EXISTS " + TABLE3_NAME + " ("
                + TABLE3_EMAIL + " TEXT, "
                + TABLE3_PASS + " TEXT);";
        db.execSQL(query);

        //CREATE TABLE IF NOT EXISTS Orders (user TEXT, restaurant TEXT, name TEXT, image TEXT, price FLOAT, orderTime DATETIME DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (restaurant) REFERENCES Restaurants(id), FOREIGN KEY (user) REFERENCES Users(email));
        query = "CREATE TABLE IF NOT EXISTS " + TABLE4_NAME + " ("
                + TABLE4_USER + " TEXT, "
                + TABLE4_RESTAURANT_ID + " TEXT, "
                + TABLE4_FOOD + " TEXT, "
                + TABLE4_IMAGE + " TEXT, "
                + TABLE4_PRICE + " FLOAT, "
                + TABLE4_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
                + "FOREIGN KEY (" + TABLE4_RESTAURANT_ID + ") REFERENCES " + TABLE1_NAME + "(" + TABLE1_ID + "), "
                + "FOREIGN KEY (" + TABLE4_USER + ") REFERENCES " + TABLE3_NAME + "(" + TABLE3_EMAIL + "));";
        db.execSQL(query);

        //Importing data to Restaurants
        db.execSQL("INSERT INTO " + TABLE1_NAME + " VALUES ('kfc1', 'KFC', 'kfc1');");
        db.execSQL("INSERT INTO " + TABLE1_NAME + " VALUES ('pzht', 'Pizza Hut', 'pzht');");
        db.execSQL("INSERT INTO " + TABLE1_NAME + " VALUES ('mcds', 'McDonalds', 'mcds');");
        db.execSQL("INSERT INTO " + TABLE1_NAME + " VALUES ('domi', 'Dominos', 'domi');");
        db.execSQL("INSERT INTO " + TABLE1_NAME + " VALUES ('strb', 'Starbucks', 'strb');");
        db.execSQL("INSERT INTO " + TABLE1_NAME + " VALUES ('chck', 'The Cheesecake Factory', 'chck');");
        db.execSQL("INSERT INTO " + TABLE1_NAME + " VALUES ('hpun', 'The Three Broomsticks', 'hpun');");
        db.execSQL("INSERT INTO " + TABLE1_NAME + " VALUES ('cndm', 'The Candy Man', 'cndm');");
        db.execSQL("INSERT INTO " + TABLE1_NAME + " VALUES ('tkks', 'The Krusty Krab', 'tkks');");
        db.execSQL("INSERT INTO " + TABLE1_NAME + " VALUES ('ppts', 'Pizza Planet', 'ppts');");
        db.execSQL("INSERT INTO " + TABLE1_NAME + " VALUES ('sdcm', 'Burger King', 'sdcm');");

        //Importing data to Food - 'kfc1'
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('kfc1', '5pc Bucket', 'kfc1_bucket', 6.19, 'Its Finger Licking’ Good');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('kfc1', '10pc Bucket', 'kfc1_bucket', 8.99, 'Its Finger Licking’ Good');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('kfc1', 'Ultimate Savings Bucket', 'kfc1_bucket', 6.99, 'Its Finger Licking’ Good');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('kfc1', 'Mingles Bucket Meal', 'kfc1_meal', 4.79, 'Its Finger Licking’ Good');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('kfc1', 'KFC Favourites', 'kfc1_meal', 4.19, 'Its Finger Licking’ Good');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('kfc1', 'Chick & Share', 'kfc1_meal', 4.48, 'Its Finger Licking’ Good');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('kfc1', 'Classic Chicken Biriyani', 'kfc1_bucket', 4.75, 'Its Finger Licking’ Good');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('kfc1', 'Veg Biriyani Bucket', 'kfc1_bucket', 1.88, 'Its Finger Licking’ Good');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('kfc1', 'Chicken & Fries Bucket', 'kfc1_bucket', 2.99, 'Its Finger Licking’ Good');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('kfc1', 'Veg Patty', 'kfc1_burger', 1.55, 'Its Finger Licking’ Good');");

        //Importing data to Food - 'pzht'
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('pzht', 'Italian Classic', 'pzht_pizza', 2.99, 'No-one outpizzas the hut');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('pzht', 'Fresco', 'pzht_pizza', 3.59, 'No-one outpizzas the hut');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('pzht', 'Romano', 'pzht_pizza', 3.59, 'No-one outpizzas the hut');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('pzht', 'Chicken Americana', 'pzht_pizza', 4.19, 'No-one outpizzas the hut');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('pzht', 'Qattro', 'pzht_pizza', 4.79, 'No-one outpizzas the hut');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('pzht', 'Spanish Tomato Pasta', 'pzht_pasta', 1.99, 'No-one outpizzas the hut');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('pzht', 'Creamy Pasta', 'pzht_pasta', 2.29, 'No-one outpizzas the hut');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('pzht', 'Arrabbiata Pasta', 'pzht_pasta', 2.29, 'No-one outpizzas the hut');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('pzht', 'Cheesy Pepper Pasta', 'pzht_pasta', 2.29, 'No-one outpizzas the hut');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('pzht', 'Creamy Mushroom Pasta', 'pzht_pasta', 2.89, 'No-one outpizzas the hut');");

        //Importing data to Food - 'mcds'
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('mcds', 'Triple Cheese American Veg Burger', 'mcds_burger', 2.15, 'The ice-cream machine is faulty');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('mcds', 'McSpicy Premium Veg Burger', 'mcds_burger', 2.31, 'The ice-cream machine is faulty');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('mcds', 'Triple Cheese American Chicken Burger', 'mcds_burger', 2.41, 'The ice-cream machine is faulty');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('mcds', 'McSpicy Premium Chicken Burger', 'mcds_burger', 2.49, 'The ice-cream machine is faulty');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('mcds', 'Big Spicy Chicken Wrap', 'mcds_wrap', 2.15, 'The ice-cream machine is faulty');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('mcds', 'Filet-O-Fish Burger', 'mcds_burger', 1.70, 'The ice-cream machine is faulty');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('mcds', 'Hashbrowns', 'mcds_hashbrown', 0.49, 'The ice-cream machine is faulty');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('mcds', 'Double Cheese McMuffin', 'mcds_mcmuffin', 0.90, 'The ice-cream machine is faulty');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('mcds', 'Choco Chip Muffin', 'mcds_choco_muffin', 1.35, 'The ice-cream machine is faulty');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('mcds', 'Birthday Party Package', 'mcds_party', 20.55, 'The ice-cream machine is faulty');");

        //Importing data to Food - 'domi'
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('domi', '2 Garlic Breads', 'domi_garlic', 1.98, 'We will die for your pizza');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('domi', 'Margherita', 'domi_pizza', 2.39, 'We will die for your pizza');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('domi', 'Farmhouse', 'domi_pizza', 4.59, 'We will die for your pizza');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('domi', 'Peppy Paneer', 'domi_pizza', 4.59, 'We will die for your pizza');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('domi', 'Veg Extravaganza', 'domi_pizza', 5.49, 'We will die for your pizza');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('domi', 'Moroccan Spice Pizza Pasta', 'domi_pizza', 3.29, 'We will die for your pizza');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('domi', 'The Four Cheese Pizza', 'domi_pizza', 6.39, 'We will die for your pizza');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('domi', 'Roasted Chicken Wings Peri Peri', 'domi_wings', 1.69, 'We will die for your pizza');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('domi', 'Choco Lava Cake', 'domi_choco', 1.09, 'We will die for your pizza');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('domi', 'Chicken Parcel', 'domi_pizza', 0.55, 'We will die for your pizza');");

        //Importing data to Food - 'strb'
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('strb', 'Velvet', 'strb_coffee', 99.99, 'Hi Sahus');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('strb', 'Vanilla Latte', 'strb_coffee', 99.99, 'Hi Sahus');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('strb', 'Hazelnut Latte', 'strb_coffee', 99.99, 'Hi Sahus');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('strb', 'Caramel Latte', 'strb_coffee', 99.99, 'Hi Sahus');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('strb', 'Cappuccino', 'strb_coffee', 99.99, 'Hi Sahus');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('strb', 'Cocoa Cappuccino', 'strb_coffee', 99.99, 'Hi Sahus');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('strb', 'Caramel Mocha', 'strb_coffee', 99.99, 'Hi Sahus');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('strb', 'Caramel Macchiato', 'strb_coffee', 99.99, 'Hi Sahus');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('strb', 'White Chocolate Mocha', 'strb_coffee', 99.99, 'Hi Sahus');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('strb', 'Espresso', 'strb_coffee', 99.99, 'Hi Sahus');");

        //Importing data to Food - 'chck'
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('chck', 'Quesadilla with Soy Cheese', 'chck_quesadilla', 1.23, 'No Shoes No Shirt No Sheldon');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('chck', 'Shrimp Caesar Salad with No Almonds', 'chck_salad', 2.34, 'No Shoes No Shirt No Sheldon');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('chck', 'Meat Lovers Pizza with No Meat', 'chck_pizza', 4.56, 'No Shoes No Shirt No Sheldon');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('chck', 'Barbecue Bacon Cheeseburger', 'chck_burger', 7.89, 'No Shoes No Shirt No Sheldon');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('chck', 'Little House Salad', 'chck_salad', 7.95, 'No Shoes No Shirt No Sheldon');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('chck', 'Korean Fried Cauliflower', 'chck_cauli', 9.95, 'No Shoes No Shirt No Sheldon');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('chck', 'Avocado Eggrolls', 'chck_eggrolls', 15.95, 'No Shoes No Shirt No Sheldon');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('chck', 'Fried Mac & Cheese', 'chck_mac', 15.95, 'No Shoes No Shirt No Sheldon');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('chck', 'French Dip Cheeseburger', 'chck_burger', 16.95, 'No Shoes No Shirt No Sheldon');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('chck', 'Chicken Parmesan Sandwich', 'chck_sandwich', 17.95, 'No Shoes No Shirt No Sheldon');");

        //Importing data to Food - 'hpun'
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('hpun', 'Butterbeer', 'hpun_butterbeer', 7.99, 'accio butterbeer!');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('hpun', 'Frozen Butterbeer', 'hpun_butterbeer', 7.99, 'accio butterbeer!');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('hpun', 'Hot Butterbeer', 'hpun_butterbeer', 7.99, 'accio butterbeer!');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('hpun', 'Pumpkin Juice', 'hpun_pumpking', 7.99, 'accio butterbeer!');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('hpun', 'Draught Beer', 'hpun_beer', 12.00, 'Served by Aberforth Dumbledore');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('hpun', 'Butterbeer Potted Cream', 'hpun_butterbeer', 5.99, 'accio butterbeer!');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('hpun', 'Butterbeer Ice Cream', 'hpun_butterbeer', 5.99, 'accio butterbeer!');");

        //Importing data to Food - 'cndm'
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('cndm', 'Wonka Bar', 'cndm_chocolate', 1.99, 'Made by oompa loompas'  );");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('cndm', 'Everlasting Gobstoppers', 'cndm_gobstopper', 1.99, 'Made by oompa loompas');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('cndm', 'Fizzy Lifting Drinks', 'cndm_fizzy', 1.99, 'Made by oompa loompas');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('cndm', 'Hair Toffee', 'cndm_toffee', 1.99, 'Made by oompa loompas');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('cndm', 'Lickable Wallpaper', 'cndm_wallpaper', 1.99, 'Made by oompa loompas');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('cndm', 'Wonka Gum', 'cndm_gum', 1.99, 'Made by oompa loompas');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('cndm', 'Broccoli Millionaires', 'cndm_chocolate', 1.99, 'Made by oompa loompas');");

        //Importing data to Food - 'tkks'
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('tkks', 'Krabby Patty', 'tkks_patty', 1.25, 'Made by Spongebob');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('tkks', 'Krabby Meal', 'tkks_meal', 3.50, 'Made by Spongebob');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('tkks', 'Salty Sea Dog', 'tkks_hotdog', 1.25, 'Made by Spongebob');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('tkks', 'Sailors Surprise', 'tkks_sailor', 3.00, 'Made by Spongebob');");

        //Importing data to Food - 'ppts'
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('ppts', 'Pizza', 'ppts_pizza', 9.99, 'Toy Story 4');");

        //Importing data to Food - scdm
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('sdcm', 'Chicken Whopper', 'scdm_burger', 1.99, 'Eat with a king!');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('sdcm', 'Veg Whopper', 'scdm_burger', 1.69, 'Eat with a king!');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('sdcm', 'Mutton Whopper', 'scdm_burger', 2.99, 'Eat with a king!');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('sdcm', 'Hot N Cheesy', 'scdm_burger', 1.99, 'Eat with a king!');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('sdcm', 'Fries', 'scdm_fries', 1.09, 'Eat with a king!');");
    }

    //NAME: addUser
    //PURPOSE: Function to add new users to the database.
    //INPUTS:
    //          String email - email address of the new user
    //          String pass - password of the new user
    //OUTPUTS: NONE
    public void addUser (String email, String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE3_EMAIL, email);
        values.put(TABLE3_PASS, pass);
        db.insert(TABLE3_NAME, null, values);
        db.close();
    }

    //NAME: addOrder
    //PURPOSE: Function to add an order to the database, when the checkout is successful.
    //INPUTS:
    //          String user - email address of the user who placed the order
    //          List<Food> bucket - the list of food items placed in the order
    //OUTPUTS: NONE
    public void addOrder (String user, List<Food> bucket)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        for (Food food : bucket) {
            ContentValues values = new ContentValues();
            values.put(TABLE4_USER, user);
            values.put(TABLE4_RESTAURANT_ID, food.getRestaurant());
            values.put(TABLE4_FOOD, food.getName());
            values.put(TABLE4_IMAGE, food.getImage());
            values.put(TABLE4_PRICE, food.getPriceStr());
            db.insert(TABLE4_NAME, null, values);
        }

        db.close();
    }

    //NAME: emailExists
    //PURPOSE: Check if an email address exists in the database.
    //INPUTS: String email - email address of the user who is trying to log in
    //OUTPUTS: boolean - true if the email exists, otherwise false
    public boolean emailExists(String email)                {return this.getReadableDatabase().rawQuery("SELECT * FROM Users WHERE email = '" + email + "';", null).getCount() >= 1;}

    //NAME: correctPass
    //PURPOSE: Check if the email and password combination is valid.
    //INPUTS:
    //          String email - email address of the user who is trying to log in
    //          String pass - password belonging to the aforementioned email address
    //OUTPUTS: boolean - true if the combination is correct, otherwise false
    public boolean correctPass (String email, String pass)  {return this.getReadableDatabase().rawQuery("SELECT * FROM Users WHERE email = '" + email + "' AND pass = '" + pass + "';", null).getCount() >= 1;}

    //NAME: getDBAsList
    //PURPOSE: Convert the contents of the database to a list, which can be displayed by the recyclerview.
    //INPUTS: NONE
    //OUTPUTS: List<Restaurant> - List containing all the restaurants and their food items.
    public List<Restaurant> getDBAsList ()
    {
        Cursor cursor1 = this.getReadableDatabase().rawQuery("SELECT * FROM Restaurants;", null);
        List<Restaurant> restaurants = new ArrayList<>();
        if (cursor1.moveToFirst())
        {
            do
            {
                List<Food> foodList = new ArrayList<>();
                Cursor cursor2 = this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE2_NAME + " WHERE restaurant = '" + cursor1.getString(0) + "';", null);
                if (cursor2.moveToFirst()) {do {foodList.add(new Food(cursor2.getString(0), cursor2.getString(1), cursor2.getString(2), cursor2.getString(3), cursor2.getString(4)));} while (cursor2.moveToNext());}
                restaurants.add(new Restaurant(cursor1.getString(0), cursor1.getString(1), cursor1.getString(2), foodList));
                cursor2.close();
            } while (cursor1.moveToNext());
        }
        cursor1.close();

        return restaurants;
    }

    //NAME: getRandomFood
    //PURPOSE: Create a random list of food to display on the starting page.
    //INPUTS: NONE
    //OUTPUTS: List<Food> - List containing all the restaurants and their food items.
    public List<Food> getRandomFood ()
    {
        Cursor cursor2 = this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE2_NAME + ";", null);
        List<Food> randomList = new ArrayList<>(), fullList = new ArrayList<>();
        if (cursor2.moveToFirst())  {do  {fullList.add(new Food(cursor2.getString(0), cursor2.getString(1), cursor2.getString(2), cursor2.getString(3), cursor2.getString(4)));} while (cursor2.moveToNext());}
        cursor2.close();
        int randomNo = (int) (Math.random() * (fullList.size() + 1))/3;
        for (int i = 0; i < randomNo - 1; i++)  randomList.add(fullList.get((int) (Math.random() * fullList.size())));
        return randomList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
