package com.example.licapp;

public class FeedBackData {
    String title,image,key,time,date;

    public FeedBackData() {
    }

    public FeedBackData(String title, String image, String key, String time, String date) {
        this.title = title;
        this.image = image;
        this.key = key;
        this.time = time;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}