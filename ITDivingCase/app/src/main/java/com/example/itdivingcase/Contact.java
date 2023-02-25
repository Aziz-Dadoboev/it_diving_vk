package com.example.itdivingcase;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Contact {
    int image;
    String name;
    int backGroundColor;

    public Contact(int image, String name, int backGroundColor) {
        this.image = image;
        this.name = name;
        this.backGroundColor = backGroundColor;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBackGroundColor() {
        return backGroundColor;
    }

    public void setBackGroundColor(int backGroundColor) {
        this.backGroundColor = backGroundColor;
    }
}