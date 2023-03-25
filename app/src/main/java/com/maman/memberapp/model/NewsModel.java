package com.maman.memberapp.model;

public class NewsModel {
    String title, date, content;

    public NewsModel(String title, String date, String content) {
        this.title = title;
        this.date = date;
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }
}
