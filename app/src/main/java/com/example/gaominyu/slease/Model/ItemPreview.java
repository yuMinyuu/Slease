package com.example.gaominyu.slease.Model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class ItemPreview {

    public String title;
    public int categoryID;
    public String rate;
    public int frequencyID;
    public String imageBase64;

    // Default constructor required for calls to
    // DataSnapshot.getValue(Item.class)
    public ItemPreview() {
    }

    public ItemPreview(String title, int categoryID, String rate, int frequencyID, String imageBase64){
        this.title = title;
        this.categoryID = categoryID;
        this.rate = rate;
        this.frequencyID = frequencyID;
        this.imageBase64 = imageBase64;
    }

}
