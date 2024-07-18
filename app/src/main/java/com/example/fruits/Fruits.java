package com.example.fruits;

public class Fruits {

    private String name;
    private String description;
    private int imageResourceId;

    public Fruits (String name, String description, int imageResourceId){
        this.name = name;
        this.description= description;
        this.imageResourceId = imageResourceId;
    }

    public String getName(){ return name; }
    public String getDescription(){ return description; }
    public int getImageResourceId(){return imageResourceId;}

    @Override
    public String toString(){ return name;}

}
