package com.example.biblio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "contactDb";
    public static final String TABLE_CONTACTS = "contacts";

    public static final String TABLE_BOOKS = "books";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_MAIL = "mail";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_BOOK_NAME = "book_name";
    public static final String KEY_AUTHOR = "author";
    public static final String KEY_SUMMARY = "summary";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createContactsTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + " (" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_NAME + " TEXT," +
                KEY_MAIL + " TEXT," +
                KEY_PASSWORD + " TEXT)";
        db.execSQL(createContactsTableQuery);

        String createBooksTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_BOOKS + " (" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_BOOK_NAME + " TEXT," +
                KEY_AUTHOR + " TEXT," +
                KEY_SUMMARY + " TEXT)";
        db.execSQL(createBooksTableQuery);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        onCreate(db);

    }

}