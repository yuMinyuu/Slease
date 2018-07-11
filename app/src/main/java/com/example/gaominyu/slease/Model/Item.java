package com.example.gaominyu.slease.Model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Item {
    public String title;
    public String description;
    public int categoryID;
    public String imageUrls;
    public String deposit;
    public String rate;
    public int frequencyID;
    public boolean allowCash;
    public boolean allowTransfer;

    // Default constructor required for calls to
    // DataSnapshot.getValue(Item.class)
    public Item() {
    }

    public Item(String title, String description, int categoryID, String imageUrls, String deposit,
                String rate, int frquencyID, boolean allowCash, boolean allowTransfer){
        this.title = title;
        this.description = description;
        this.categoryID = categoryID;
        this.imageUrls = imageUrls;
        this.deposit = deposit;
        this.rate = rate;
        this.frequencyID = frquencyID;
        this.allowCash = allowCash;
        this.allowTransfer = allowTransfer;
    }

//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getImage() {
//        return imageUrl;
//    }
//
//    public void setImage(String imageUrl) {
//        this.imageUrl = imageUrl;
//    }
//
//    public String getPrice() {
//        return price;
//    }
//
//    public void setPrice(String price) {
//        this.price = price;
//    }
}
