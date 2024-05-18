package com.example.mirea_prac_10.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MyDbManager {
    private Context context;
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
        List<String> tempList = new ArrayList<>();
        Cursor cursor = db.query(MyConstans.TABLE_NAME,null,null,
                null,null,null,null);

        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow(MyConstans.TITLE));
            tempList.add(title);
        }
        cursor.close();
        return tempList;
    }

    public void deleteFromDb() {
        db.delete(MyConstans.TABLE_NAME,"title" + " = ?",new String[] {"ПОГОДА"});
        db.close();
    }

    public void updateDb(String title, String disc) {
        ContentValues cv = new ContentValues();
        cv.put(MyConstans.TITLE,title);
        cv.put(MyConstans.DISC,disc);
        db.update(MyConstans.TABLE_NAME,cv,"title" + " = ?", new String[] {"ПОГОДА"});
    }



    public void closeDb() {
        myDbHelper.close();
    }



}
