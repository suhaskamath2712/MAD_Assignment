package com.example.assignment1;

public class Food
{
    private final String restaurant, name, image, desc;
    private final double price;

    public Food(String restaurant, String name, String image, String price, String desc)
    {
        this.restaurant = restaurant;
        this.name = name;
        this.image = image;
        this.price = Double.parseDouble(price);
        this.desc = desc;
    }

    public String getRestaurant() {return restaurant;}
    public String getName() {return name;}
    public String getImage() {return image;}
    public String getDesc() {return desc;}
    public double getPrice() {return price;}
    public String getPriceStr() {return Double.toString(price);}
    public Food copy()  {return new Food(restaurant, name, image, Double.toString(price), desc);}
}
