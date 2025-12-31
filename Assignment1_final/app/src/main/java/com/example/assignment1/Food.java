package com.example.assignment1;

public class Food
{
    private final String restaurant, name, image, desc;
    private final double price;

    //NAME: Food
    //PURPOSE: Constructor for food.
    //INPUTS:
    //          String restaurant - ID of the restaurant
    //          String name - Name of the food
    //          String image - Image file name of the food
    //          String price - Price of the food
    //          String desc - Description of the food
    //OUTPUTS: NONE
    public Food(String restaurant, String name, String image, String price, String desc)
    {
        this.restaurant = restaurant;
        this.name = name;
        this.image = image;
        this.price = Double.parseDouble(price);
        this.desc = desc;
    }

    //getters for each variable stored in an object of the class
    public String getRestaurant() {return restaurant;}
    public String getName() {return name;}
    public String getImage() {return image;}
    public String getDesc() {return desc;}
    public double getPrice() {return price;}
    public String getPriceStr() {return Double.toString(price);}

    //NAME: copy
    //PURPOSE: The message to copy a food item, such as when it needs to be added to a bucket.
    //INPUTS: NONE
    //OUTPUTS: Food - which is a copy of this food item
    public Food copy()  {return new Food(restaurant, name, image, Double.toString(price), desc);}
}
