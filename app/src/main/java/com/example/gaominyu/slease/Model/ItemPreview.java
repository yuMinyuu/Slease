package com.example.gaominyu.slease.Model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class ItemPreview {

    public String title;
    public int categoryID;
    public String mainImageUrl;
    public String rate;
    public int frequencyID;

    // Default constructor required for calls to
    // DataSnapshot.getValue(Item.class)
    public ItemPreview() {
    }

    public ItemPreview(String title,int categoryID, String mainImageUrl, String rate, int frquencyID){
        this.title = title;
        this.categoryID = categoryID;
        this.mainImageUrl = mainImageUrl;
        this.rate = rate;
        this.frequencyID = frquencyID;
    }

}
