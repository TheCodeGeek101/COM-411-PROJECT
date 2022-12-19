package com.example.smartassaultapplication.domain;

import android.widget.ImageView;

public class NewsData {
    String title;
    String description;
    String urlToImage;
    String url;
    ImageView imageView;

    public NewsData(String urlToImage, String description, String url, String title, ImageView imageView) {
        this.title = title;
        this.description = description;
        this.urlToImage = urlToImage;
        this.url = url;
        this.imageView = imageView;
    }
}
