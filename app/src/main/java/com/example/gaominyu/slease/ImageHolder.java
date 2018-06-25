package com.example.gaominyu.slease;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class ImageHolder {

    private static ImageHolder singleton;
    private ArrayList<Bitmap> images;

    private ImageHolder () {}

    public ArrayList<Bitmap> getImages() {
        return this.images;
    }

    public void setImages(ArrayList<Bitmap> images) {
        this.images = images;
    }

    public static ImageHolder getSingleton() {
        if (singleton == null)
            singleton = new ImageHolder();
        return singleton;
    }

    public void clearHolder() {
        this.images = new ArrayList<>();
    }

    public boolean isEmpty() {
        return this.images.isEmpty();
    }
}
