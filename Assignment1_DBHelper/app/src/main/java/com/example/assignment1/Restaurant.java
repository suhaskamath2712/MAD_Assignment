package com.example.assignment1;

import java.util.List;

public class Restaurant
{
    private String id, name, image;
    private List<Food> foodList;

    public Restaurant(String id, String name, String image, List<Food> foodList) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.foodList = foodList;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getImage() {
        return image;
    }
    public List<Food> getFoodList() {return foodList;}
}
