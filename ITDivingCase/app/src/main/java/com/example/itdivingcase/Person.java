package com.example.itdivingcase;

public class Person {
    private String name;
    private String number;
    private int image;

    public Person(String name, String number, int image) {
        this.name = name;
        this.number = number;
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public int getImage() {
        return image;
    }
}
