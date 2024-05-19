package com.example.mirea_prac_10;

import static java.security.AccessController.getContext;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.mirea_prac_10.db.DatabaseHelper;
import com.example.mirea_prac_10.db.MyDbManager;

public class ContactProvider  extends ContentProvider {
    private SQLiteDatabase db;

    public MyDbManager myDbManager;
    public static final Uri CONTENT_URI = Uri.parse("content://com.example.mirea_prac_10.provider/contacts");

    public boolean onCreate()
    {
        Context context = getContext();
        myDbManager = new MyDbManager(context);
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return (db != null);
    }


    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        return db.query("my_table", projection, selection, selectionArgs, null, null, sortOrder);
    }


    public Uri insert(Uri uri, ContentValues values)
    {
        long rowID = db.insert("my_table", "", values);
        Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
        myDbManager.context.getContentResolver().notifyChange(_uri, null);
        return _uri;
    }


    public int delete(Uri uri, String selection, String[] selectionArgs)
    {
        int count = db.delete("my_table", selection, selectionArgs);
        myDbManager.context.getContentResolver().notifyChange(uri, null);
        return count;
    }


    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)
    {
        int count = db.update("my_table", values, selection, selectionArgs);
        myDbManager.context.getContentResolver().notifyChange(uri, null);
        return count;
    }


    public String getType(Uri uri)
    {
        return "vnd.android.cursor.dir/vnd.example.contacts";
    }
}


