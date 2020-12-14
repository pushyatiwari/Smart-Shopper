package com.example.madproject4.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHistory extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "history.db";
    public static final String TABLE_NAME = "history_table";
    public static final String COL_1 = "ID";//0
    public static final String COL_2 = "TITLE";//1
    public static final String COL_3 = "COUNT";//2

    public DatabaseHistory(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
   //       SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID TEXT PRIMARY KEY ," +
                "TITLE TEXT UNIQUE,COUNT INTEGER UNIQUE)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String id,String title, int count) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,title);
        contentValues.put(COL_3,count);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        return result != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public  int getMax(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(" select max(COUNT) as COUNT from " + TABLE_NAME,null);
        if (res != null) {
            res.moveToFirst();
            int count= res.getInt(0);
            return  count;
        }
        return  -1;
    }


    public void deleteitem(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"title=?",new String[]{title});
    }



}
