package com.example.assignment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
    public DBHelper(@Nullable Context context) {super(context, DB_NAME, null, DB_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db)
    {
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
        db.execSQL("INSERT INTO " + TABLE1_NAME + " VALUES ('kfc1', 'KFC', 'food.jpg');");
        db.execSQL("INSERT INTO " + TABLE1_NAME + " VALUES ('pzht', 'Pizza Hut', 'food.jpg');");
        db.execSQL("INSERT INTO " + TABLE1_NAME + " VALUES ('mcds', 'McDonalds', 'food.jpg');");
        db.execSQL("INSERT INTO " + TABLE1_NAME + " VALUES ('domi', 'Dominos', 'food.jpg');");
        db.execSQL("INSERT INTO " + TABLE1_NAME + " VALUES ('strb', 'Starbucks', 'food.jpg');");
        db.execSQL("INSERT INTO " + TABLE1_NAME + " VALUES ('chck', 'The Cheesecake Factory', 'food.jpg');");
        db.execSQL("INSERT INTO " + TABLE1_NAME + " VALUES ('hpun', 'The Three Broomsticks', 'food.jpg');");
        db.execSQL("INSERT INTO " + TABLE1_NAME + " VALUES ('cndm', 'The Candy Man', 'food.jpg');");
        db.execSQL("INSERT INTO " + TABLE1_NAME + " VALUES ('tkks', 'The Krusty Krab', 'food.jpg');");
        db.execSQL("INSERT INTO " + TABLE1_NAME + " VALUES ('ppts', 'Pizza Planet', 'food.jpg');");
        db.execSQL("INSERT INTO " + TABLE1_NAME + " VALUES ('sdcm', 'Burger King', 'food.jpg');");

        //Importing data to Food - 'kfc1'
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('kfc1', '5pc Bucket', 'food.jpg', 6.19, 'Its Finger Licking’ Good');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('kfc1', '10pc Bucket', 'food.jpg', 8.99, 'Its Finger Licking’ Good');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('kfc1', 'Ultimate Savings Bucket', 'food.jpg', 6.99, 'Its Finger Licking’ Good');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('kfc1', 'Mingles Bucket Meal', 'food.jpg', 4.79, 'Its Finger Licking’ Good');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('kfc1', 'KFC Favourites', 'food.jpg', 4.19, 'Its Finger Licking’ Good');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('kfc1', 'Chick & Share', 'food.jpg', 4.48, 'Its Finger Licking’ Good');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('kfc1', 'Classic Chicken Biriyani', 'food.jpg', 4.75, 'Its Finger Licking’ Good');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('kfc1', 'Veg Biriyani Bucket', 'food.jpg', 1.88, 'Its Finger Licking’ Good');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('kfc1', 'Chicken & Fries Bucket', 'food.jpg', 2.99, 'Its Finger Licking’ Good');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('kfc1', 'Veg Patty', 'food.jpg', 1.55, 'Its Finger Licking’ Good');");

        //Importing data to Food - 'pzht'
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('pzht', 'Italian Classic', 'food.jpg', 2.99, 'No-one outpizzas the hut');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('pzht', 'Fresco', 'food.jpg', 3.59, 'No-one outpizzas the hut');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('pzht', 'Romano', 'food.jpg', 3.59, 'No-one outpizzas the hut');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('pzht', 'Chicken Americana', 'food.jpg', 4.19, 'No-one outpizzas the hut');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('pzht', 'Qattro', 'food.jpg', 4.79, 'No-one outpizzas the hut');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('pzht', 'Spanish Tomato Pasta', 'food.jpg', 1.99, 'No-one outpizzas the hut');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('pzht', 'Creamy Pasta', 'food.jpg', 2.29, 'No-one outpizzas the hut');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('pzht', 'Arrabbiata Pasta', 'food.jpg', 2.29, 'No-one outpizzas the hut');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('pzht', 'Cheesy Pepper Pasta', 'food.jpg', 2.29, 'No-one outpizzas the hut');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('pzht', 'Creamy Mushroom Pasta', 'food.jpg', 2.89, 'No-one outpizzas the hut');");

        //Importing data to Food - 'mcds'
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('mcds', 'Triple Cheese American Veg Burger', 'food.jpg', 2.15, 'The ice-cream machine is faulty');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('mcds', 'McSpicy Premium Veg Burger', 'food.jpg', 2.31, 'The ice-cream machine is faulty');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('mcds', 'Triple Cheese American Chicken Burger', 'food.jpg', 2.41, 'The ice-cream machine is faulty');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('mcds', 'McSpicy Premium Chicken Burger', 'food.jpg', 2.49, 'The ice-cream machine is faulty');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('mcds', 'Big Spicy Chicken Wrap', 'food.jpg', 2.15, 'The ice-cream machine is faulty');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('mcds', 'Filet-O-Fish Burger', 'food.jpg', 1.70, 'The ice-cream machine is faulty');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('mcds', 'Hashbrowns', 'food.jpg', 0.49, 'The ice-cream machine is faulty');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('mcds', 'Double Cheese McMuffin', 'food.jpg', 0.90, 'The ice-cream machine is faulty');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('mcds', 'Choco Chip Muffin', 'food.jpg', 1.35, 'The ice-cream machine is faulty');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('mcds', 'Birthday Party Package', 'food.jpg', 20.55, 'The ice-cream machine is faulty');");

        //Importing data to Food - 'domi'
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('domi', '2 Garlic Breads', 'food.jpg', 1.98, 'We will die for your pizza');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('domi', 'Margherita', 'food.jpg', 2.39, 'We will die for your pizza');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('domi', 'Farmhouse', 'food.jpg', 4.59, 'We will die for your pizza');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('domi', 'Peppy Paneer', 'food.jpg', 4.59, 'We will die for your pizza');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('domi', 'Veg Extravaganza', 'food.jpg', 5.49, 'We will die for your pizza');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('domi', 'Moroccan Spice Pizza Pasta', 'food.jpg', 3.29, 'We will die for your pizza');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('domi', 'The Four Cheese Pizza', 'food.jpg', 6.39, 'We will die for your pizza');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('domi', 'Roasted Chicken Wings Peri Peri', 'food.jpg', 1.69, 'We will die for your pizza');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('domi', 'Choco Lava Cake', 'food.jpg', 1.09, 'We will die for your pizza');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('domi', 'Chicken Parcel', 'food.jpg', 0.55, 'We will die for your pizza');");

        //Importing data to Food - 'strb'
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('strb', 'Velvet', 'food.jpg', 99.99, 'Hi Sahus');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('strb', 'Vanilla Latte', 'food.jpg', 99.99, 'Hi Sahus');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('strb', 'Hazelnut Latte', 'food.jpg', 99.99, 'Hi Sahus');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('strb', 'Caramel Latte', 'food.jpg', 99.99, 'Hi Sahus');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('strb', 'Cappuccino', 'food.jpg', 99.99, 'Hi Sahus');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('strb', 'Cocoa Cappuccino', 'food.jpg', 99.99, 'Hi Sahus');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('strb', 'Caramel Mocha', 'food.jpg', 99.99, 'Hi Sahus');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('strb', 'Caramel Macchiato', 'food.jpg', 99.99, 'Hi Sahus');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('strb', 'White Chocolate Mocha', 'food.jpg', 99.99, 'Hi Sahus');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('strb', 'Espresso', 'food.jpg', 99.99, 'Hi Sahus');");

        //Importing data to Food - 'chck'
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('chck', 'Quesadilla with Soy Cheese', 'food.jpg', 1.23, 'No Shoes No Shirt No Sheldon');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('chck', 'Shrimp Caesar Salad with No Almonds', 'food.jpg', 2.34, 'No Shoes No Shirt No Sheldon');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('chck', 'Meat Lovers Pizza with No Meat', 'food.jpg', 4.56, 'No Shoes No Shirt No Sheldon');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('chck', 'Barbecue Bacon Cheeseburger', 'food.jpg', 7.89, 'No Shoes No Shirt No Sheldon');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('chck', 'Little House Salad', 'food.jpg', 7.95, 'No Shoes No Shirt No Sheldon');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('chck', 'Korean Fried Cauliflower', 'food.jpg', 9.95, 'No Shoes No Shirt No Sheldon');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('chck', 'Avocado Eggrolls', 'food.jpg', 15.95, 'No Shoes No Shirt No Sheldon');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('chck', 'Fried Mac & Cheese', 'food.jpg', 15.95, 'No Shoes No Shirt No Sheldon');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('chck', 'French Dip Cheeseburger', 'food.jpg', 16.95, 'No Shoes No Shirt No Sheldon');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('chck', 'Chicken Parmesan Sandwich', 'food.jpg', 17.95, 'No Shoes No Shirt No Sheldon');");

        //Importing data to Food - 'hpun'
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('hpun', 'Butterbeer', 'food.jpg', 7.99, 'accio butterbeer!');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('hpun', 'Frozen Butterbeer', 'food.jpg', 7.99, 'accio butterbeer!');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('hpun', 'Hot Butterbeer', 'food.jpg', 7.99, 'accio butterbeer!');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('hpun', 'Pumpkin Juice', 'food.jpg', 7.99, 'accio butterbeer!');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('hpun', 'Draught Beer', 'food.jpg', 12.00, 'Served by Aberforth Dumbledore');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('hpun', 'Butterbeer Potted Cream', 'food.jpg', 5.99, 'accio butterbeer!');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('hpun', 'Butterbeer Ice Cream', 'food.jpg', 5.99, 'accio butterbeer!');");

        //Importing data to Food - 'cndm'
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('cndm', 'Wonka Bar', 'food.jpg', 1.99, 'Made by oompa loompas'  );");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('cndm', 'Everlasting Gobstoppers', 'food.jpg', 1.99, 'Made by oompa loompas');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('cndm', 'Fizzy Lifting Drinks', 'food.jpg', 1.99, 'Made by oompa loompas');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('cndm', 'Hair Toffee', 'food.jpg', 1.99, 'Made by oompa loompas');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('cndm', 'Lickable Wallpaper', 'food.jpg', 1.99, 'Made by oompa loompas');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('cndm', 'Wonka Gum', 'food.jpg', 1.99, 'Made by oompa loompas');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('cndm', 'Broccoli Millionaires', 'food.jpg', 1.99, 'Made by oompa loompas');");

        //Importing data to Food - 'tkks'
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('tkks', 'Krabby Patty', 'food.jpg', 1.25, 'Made by Spongebob');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('tkks', 'Krabby Meal', 'food.jpg', 3.50, 'Made by Spongebob');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('tkks', 'Salty Sea Dog', 'food.jpg', 1.25, 'Made by Spongebob');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('tkks', 'Sailors Surprise', 'food.jpg', 3.00, 'Made by Spongebob');");

        //Importing data to Food - 'ppts'
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('ppts', 'Pizza', 'food.jpg', 9.99, 'Toy Story 4');");

        //Importing data to Food - scdm
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('sdcm', 'Chicken Whopper', 'food.jpg', 1.99, 'Eat with a king!');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('sdcm', 'Veg Whopper', 'food.jpg', 1.69, 'Eat with a king!');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('sdcm', 'Mutton Whopper', 'food.jpg', 2.99, 'Eat with a king!');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('sdcm', 'Hot N Cheesy', 'food.jpg', 1.99, 'Eat with a king!');");
        db.execSQL("INSERT INTO " + TABLE2_NAME + " VALUES ('sdcm', 'Fries', 'food.jpg', 1.09, 'Eat with a king!');");
    }

    public void addRestaurant (String id, String name, String image)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE1_ID, id);
        values.put(TABLE1_NAME, name);
        values.put(TABLE1_ID, image);
        db.insert(TABLE1_NAME, null, values);
        db.close();
    }

    public void addFood (String restaurant, String name, String image, double price, String description)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE2_RESTAURANT_ID, restaurant);
        values.put(TABLE2_FOOD_NAME, name);
        values.put(TABLE2_IMAGE, image);
        values.put(TABLE2_PRICE, price);
        values.put(TABLE2_DESCRIPTION, description);
        db.insert(TABLE2_NAME, null, values);
        db.close();
    }

    public void addUser (String email, String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE3_EMAIL, email);
        values.put(TABLE3_PASS, pass);
        db.insert(TABLE3_NAME, null, values);
        db.close();
    }

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

    public List<Restaurant> getDBAsList ()
    {
        Cursor cursor1 = this.getReadableDatabase().rawQuery("SELECT * FROM Restaurants;", null);
        List<Restaurant> restaurants = new ArrayList<Restaurant>();
        if (cursor1.moveToFirst())
        {
            do
            {
                List<Food> foodList = new ArrayList<Food>();
                Cursor cursor2 = this.getReadableDatabase().rawQuery("SELECT * FROM Food WHERE restaurant = '" + cursor1.getString(0) + "';", null);
                if (cursor2.moveToFirst()) {do {foodList.add(new Food(cursor2.getString(0), cursor2.getString(1), cursor2.getString(2), cursor2.getString(3), cursor2.getString(4)));} while (cursor2.moveToNext());}
                foodList.iterator();
                restaurants.add(new Restaurant(cursor1.getString(0), cursor1.getString(1), cursor1.getString(2), foodList));
                cursor2.close();
            } while (cursor1.moveToNext());
        }
        cursor1.close();

        return restaurants;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
