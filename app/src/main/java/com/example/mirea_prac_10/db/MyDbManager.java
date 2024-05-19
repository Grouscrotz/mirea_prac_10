package com.example.mirea_prac_10.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MyDbManager {
    public Context context;
    private DatabaseHelper myDbHelper;
    private SQLiteDatabase db;

    public MyDbManager(Context context) {
        this.context = context;
        myDbHelper = new DatabaseHelper(context);
    }

    public void openDb() {
        db = myDbHelper.getWritableDatabase();
    }

    public void insertToDb(String title, String disc) {
        ContentValues cv = new ContentValues();
        cv.put(MyConstans.TITLE,title);
        cv.put(MyConstans.DISC,disc);
        db.insert(MyConstans.TABLE_NAME,null,cv);
    }

    public List<String> getFromDb() {
        openDb();
        List<String> tempList = new ArrayList<>();
        Cursor cursor = db.query(MyConstans.TABLE_NAME,null,null,
                null,null,null,null);

        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow(MyConstans.TITLE));
            String disc = cursor.getString(cursor.getColumnIndexOrThrow(MyConstans.DISC));
            String split =   title + "      " + disc;
            tempList.add(split);
        }
        cursor.close();
        return tempList;
    }

    public void deleteFromDb(String title, String disc) {
        myDbHelper.onOpen(db);
        db.delete(MyConstans.TABLE_NAME,"title" + " = ?",new String[] {title});

    }

    public void updateDb(String title, String disc) {
        ContentValues cv = new ContentValues();
        cv.put(MyConstans.TITLE,title);
        cv.put(MyConstans.DISC,disc);
        db.update(MyConstans.TABLE_NAME,cv,"title" + " = ?", new String[] {title});
        db.update(MyConstans.TABLE_NAME,cv,"disc" + " = ?", new String[] {disc});

    }

    public String findInBd(String disc) {
        String temp_phone  = new String();

        Cursor cursor = db.query(MyConstans.TABLE_NAME,new String[] {MyConstans._ID,MyConstans.TITLE,MyConstans.DISC},
                MyConstans.DISC + " = ?", new String[] {disc},null,null,null);
        while (cursor.moveToNext()) {
            temp_phone = cursor.getString(cursor.getColumnIndexOrThrow(MyConstans.TITLE));

        }
        cursor.close();
        return temp_phone;
    }





    public void closeDb() {
        myDbHelper.close();
    }



}
