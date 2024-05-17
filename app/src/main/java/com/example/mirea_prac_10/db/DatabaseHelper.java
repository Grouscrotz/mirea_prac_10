package com.example.mirea_prac_10.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, MyConstans.DB_NAME, null, MyConstans.DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MyConstans.TABLE_STRUCTURE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int olvVersion, int newVersion) {
        db.execSQL(MyConstans.DROP_TABLE);
        onCreate(db);
    }



}
