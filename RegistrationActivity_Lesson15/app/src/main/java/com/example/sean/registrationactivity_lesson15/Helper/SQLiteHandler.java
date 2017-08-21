package com.example.sean.registrationactivity_lesson15.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by Sean on 8/17/2017.
 */

public class SQLiteHandler extends SQLiteOpenHelper {
    public static final String TABLE_NAME ="users";
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String MAIL = "mail";
    public static final String UID = "uid";
    public static final String CREATED_TIME = "ct";
    private static final String TAG = SQLiteHandler.class.getSimpleName();
    static final String DB_NAME = "users.db";
    static final int DB_VERSION = 1;



    public SQLiteHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + _ID + " INTEGER PRIMARY KEY," + NAME + " TEXT,"
                + MAIL + " TEXT UNIQUE," + UID + " TEXT,"
                + CREATED_TIME + " TEXT" + ")";

        db.execSQL(CREATE_LOGIN_TABLE);
        Log.d(TAG, "Database tables created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void AddUser(String username,String password,String mail,String created_time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,username);
        values.put(UID,password);
        values.put(MAIL,mail);
        values.put(CREATED_TIME,created_time);
        long id = db.insert(TABLE_NAME,null,values);

        db.close();
        Log.d(TAG, "New user inserted into sqlite: " + id);
    }
    public HashMap<String,String> GetUser(){
        HashMap<String,String> user = new HashMap<String,String>();
        String select = "SELECT * FROM" + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select,null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            user.put("name", cursor.getString(1));
            user.put("mail",cursor.getString(2));
            user.put("uid",cursor.getString(3));
            user.put("ct",cursor.getString(4));
        }
        cursor.close();
        db.close();
        Log.d(TAG,"Fetching user from sqlite: " + user.toString());
        return user;
    }
    public void DeleteUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
        db.close();
        Log.d(TAG,"deleted all users from SQLite");
    }
}
