package com.epicodus.athletetracker.Models;


import org.parceler.Parcel;



@Parcel
public class News {
    String title;
    String author;
    String image;
    String url;

    public News() {}

    public News(String title, String author, String image, String url){
        this.title = title;
        this.author = author;
        this.image = image;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getImage() {
        return image;
    }

    public String getUrl() {
        return url;
    }
}
