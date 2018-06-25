package com.example.gaominyu.slease;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class ImageHolder {

    private static ImageHolder singleton;
    private ArrayList<Bitmap> images;

    private ImageHolder () {}

    public void setImages(ArrayList<Bitmap> images) {
        this.images = images;
    }

    public ImageHolder getSingleton() {
        if (singleton == null)
            singleton = new ImageHolder();
        return singleton;
    }

    public void clearHolder() {
        this.images.clear();
    }
}
