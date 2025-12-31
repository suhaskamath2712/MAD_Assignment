package com.example.assignment1;

import java.util.List;

public class Restaurant
{
    private String id, name, image;
    private List<Food> foodList;

    //NAME: Restaurant
    //PURPOSE: Constructor for Restaurant.
    //INPUTS:
    //          String id - ID of the restaurant
    //          String name - Name of the restaurant
    //          String image - Image file name of the restaurant
    //          List<Food> foodList - Menu of the restaurant
    //OUTPUTS: NONE
    public Restaurant(String id, String name, String image, List<Food> foodList) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.foodList = foodList;
    }

    //getters for each variable stored in an object of the class
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getImage()
    {
        System.out.println(image);
        return image;
    }
    public List<Food> getFoodList() {return foodList;}
}
