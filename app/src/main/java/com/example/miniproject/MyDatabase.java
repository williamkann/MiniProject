package com.example.miniproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDatabase extends SQLiteOpenHelper {
    private static final int DB_VERSION=2;
    private static final String DB_TABLE_NAME = "User";
    private static final String PKEY = "id_user";
    private static final String LOGIN = "LOGIN";
    private static final String PASSWORD = "PASSWORD";


    public MyDatabase(Context context)
    {
        super(context, DB_TABLE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String DATABASE_TABLE_CREATE = "CREATE TABLE " + DB_TABLE_NAME + " (" +
                PKEY + " INTEGER PRIMARY KEY, " +
                LOGIN + " TEXT, " +
                PASSWORD + " TEXT " + ");";
        db.execSQL(DATABASE_TABLE_CREATE);
    }

    public void insertData (String login, String password)
    {
        Log.i("INSERT"," Insert in database " + login + " " + password);
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        ContentValues values = new ContentValues();
        values.put(LOGIN, login);
        values.put(PASSWORD, password);
        db.insertOrThrow(DB_TABLE_NAME,null, values);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void readData()
    {
        Log.i("READ", "Reading database...");
        String select = new String("SELECT * from " + DB_TABLE_NAME);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        Log.i("JFL", "Number of entries: " + cursor.getCount());
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Log.i("JFL", "Reading: " + cursor.getString(cursor.getColumnIndex(LOGIN)));
                Log.i("JFL", "Reading: " + cursor.getString(cursor.getColumnIndex(PASSWORD)));
            } while (cursor.moveToNext());
        }
    }

    public boolean connection(String login, String password)
    {
        Log.i("CHECK CONNECTION", "Try to connect...");
        String select = new String("SELECT * from " + DB_TABLE_NAME);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        Log.i("JFL", "Number of entries: " + cursor.getCount());
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Log.i("JFL", "Reading: " + cursor.getString(cursor.getColumnIndex(LOGIN)));
                Log.i("JFL", "Reading: " + cursor.getString(cursor.getColumnIndex(PASSWORD)));
                if(login.equals(cursor.getString(cursor.getColumnIndex(LOGIN))) && (password.equals(cursor.getString(cursor.getColumnIndex(PASSWORD)))))
                    return true;
            } while (cursor.moveToNext());
        }
        return false;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
