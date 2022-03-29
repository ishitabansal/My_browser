package com.example.my_browser;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class databasehandler extends SQLiteOpenHelper {

    public static final String BOOKMARKS_TABLE = "BOOKMARKS_TABLE";
    public static final String COLUMN_URL_NAME = "URL_NAME";
    public static final String COLUMN_ID = "ID";

    public databasehandler(@Nullable Context context) {
        super(context, "Bookkmarks.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry = "CREATE TABLE " + BOOKMARKS_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_URL_NAME + " TEXT)";

        db.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(bookmarks bookmarks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_URL_NAME, bookmarks.getUrlname() );
        long insert = db.insert(BOOKMARKS_TABLE, null, cv);

        if(insert == -1)
        {return false;}
        else{
            return true;}
    }

    public List<String> getEveryone()
    {
        List<String> returnList = new ArrayList<>();
        String querystring = "SELECT * FROM " + BOOKMARKS_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(querystring,null);


        if(cursor.moveToFirst())
        {
            do{
                String bookmarkurl = cursor.getString(1);
                returnList.add(bookmarkurl);
            }
            while (cursor.moveToNext());

        }
        else
        {}
        cursor.close();
        db.close();
        return returnList;
    }

}
