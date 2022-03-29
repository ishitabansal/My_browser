package com.example.my_browser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class history extends AppCompatActivity {

    private int Id;
    private String historyname;

    public history(int id, String historyname) {
        Id = id;
        this.historyname = historyname;
    }

    public history() {
    }

    @Override
    public String toString() {
        return "history{" +
                "Id=" + Id +
                ", historyname='" + historyname + '\'' +
                '}';
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getHistoryname() {
        return historyname;
    }

    public void setHistoryname(String historyname) {
        this.historyname = historyname;
    }
}
