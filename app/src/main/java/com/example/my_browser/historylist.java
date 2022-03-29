package com.example.my_browser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Collections;
import java.util.List;

public class historylist extends AppCompatActivity {

    private ListView historylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historylist);

        this.setTitle("History");
        historylist = findViewById(R.id.historylist);
        mydatahandler databasehandler = new mydatahandler(historylist.this);
        List<String> everyone = databasehandler.get_Everyone();
        Collections.reverse(everyone);
        int k = everyone.size();
        if ( k > 10 )
            everyone.subList(10, k).clear();
        ArrayAdapter historysimpleArrayAdapter = new ArrayAdapter<String>(historylist.this, android.R.layout.simple_list_item_1,everyone);
        historylist.setAdapter(historysimpleArrayAdapter);

    }
}