package com.example.madproject4.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Ingredients3.db";
    public static final String TABLE_NAME = "Ingredient";
    public static final String COL_1 = "ID";//0
    public static final String COL_2 = "TITLE";//1
    public static final String COL_3 = "DESCRIPTION";//2
    public static final String COL_4= "EFFECTS";//3
    public static final String COL_5 = "HARMFUL";//4
    //list some products in which these chemicals are added in
    public static final String COL_6 = "ADDEDIN";//5
    public static final String COL_7 = "IMAGE";//6



    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, 1);
   //  SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

//          db.execSQL("create table " + TABLE_NAME + "("
//                    + COL_1 + "TEXT PRIMARY KEY ,"
//                    + COL_2 + "TEXT,"
//                    + COL_3 + "TEXT,"
//                    + COL_4 + "TEXT,"
//                    + COL_5 + "TEXT,"
//                    + COL_6 + "TEXT)"
//            );
        db.execSQL("create table " + TABLE_NAME +" (ID TEXT PRIMARY KEY ," +
                "TITLE TEXT UNIQUE,DESCRIPTION TEXT,EFFECTS TEXT," +
                "HARMFUL TEXT,ADDEDIN TEXT,IMAGE TEXT )");
        Log.d("create", "onCreate: ");

    }

    public boolean insertData(String id,String title, String desc,String eff,String harm,String add,String Imageurl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,title);
        contentValues.put(COL_3,desc);
        contentValues.put(COL_4,eff);
        contentValues.put(COL_5,harm);
        contentValues.put(COL_6,add);
        contentValues.put(COL_7,Imageurl);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        return result != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("DELETE FROM "+TABLE_NAME,null);

    }
    public Cursor getDataWith(String data) {
        SQLiteDatabase db = this.getWritableDatabase();
        //SELECT * FROM COMPANY WHERE ADDRESS  LIKE '%-%'
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" where "+COL_2+" Like '%"+data+"%'",
                null);
        return res;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public void deleteTable (){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

//    public void deleteDataTable (){
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
//    }
    public boolean updateData(String id,String title, String desc,String eff,String harm,String add,String imageUrl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,title);
        contentValues.put(COL_3,desc);
        contentValues.put(COL_4,eff);
        contentValues.put(COL_5,harm);
        contentValues.put(COL_6,add);
        contentValues.put(COL_7,imageUrl);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Cursor getData(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" where TITLE=?",
                new String [] {title});
        return res;
    }
    public Cursor getId(String Title) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("select ID from "+TABLE_NAME+" where TITLE=?",
                new String [] {Title});
        return cur;
    }
}