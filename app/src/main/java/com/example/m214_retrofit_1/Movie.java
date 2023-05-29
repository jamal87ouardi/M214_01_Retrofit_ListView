package com.example.m214_retrofit_1;

public class Movie {
    private int id;
    private String name;
    private String image;

    public Movie(int id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
