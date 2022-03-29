package com.example.my_browser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class bookmarks extends AppCompatActivity {

    private int id;
    private String urlname;

    public bookmarks(int id, String urlname) {
        this.id = id;
        this.urlname = urlname;
    }

    public bookmarks(String urlname) {
        this.urlname = urlname;
    }

    public bookmarks() {
    }

    @Override
    public String toString() {
        return "bkmarksimple{" +
                "id=" + id +
                ", urlname='" + urlname + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlname() {
        return urlname;
    }

    public void setUrlname(String urlname) {
        this.urlname = urlname;
    }
}
