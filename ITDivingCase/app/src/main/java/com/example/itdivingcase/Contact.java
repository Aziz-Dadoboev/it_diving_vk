package com.example.itdivingcase;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Contact {
    int image;
    String name;
    int id;
    int blurred;

    public Contact(int image, String name, int id, int blurred) {
        this.image = image;
        this.name = name;
        this.id = id;
        this.blurred = blurred;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getBlurred() {
        return blurred;
    }

    public void setBlurred(int blurred) {
        this.blurred = blurred;
    }
}
