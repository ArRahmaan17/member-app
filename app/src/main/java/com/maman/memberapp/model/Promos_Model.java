package com.maman.memberapp.model;

public class Promos_Model {
    String title, detail;

    public Promos_Model(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }
}
