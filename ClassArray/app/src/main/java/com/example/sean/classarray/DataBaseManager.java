package com.example.sean.classarray;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

/**
 * Created by Sean on 8/17/2017.
 */

public class DataBaseManager {
    private DataBaseHelper dbHelper;
    private SQLiteDatabase sqlDB;
    private Context context;

    public DataBaseManager(Context c){context = c;}

    public DataBaseManager open() throws SQLiteAbortException{
        dbHelper = new DataBaseHelper(context);
        sqlDB = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public void insert(String name,String address,String teacher){
        ContentValues values = new ContentValues();
        values.put(dbHelper.TEACHER,teacher);
        values.put(dbHelper.NAME,name);
        values.put(dbHelper.ADDRESS,address);
        sqlDB.insert(dbHelper.TABLE_NAME,null,values);
    }

    public void delete(long _id){
        sqlDB.delete(dbHelper.TABLE_NAME,dbHelper._ID + " = " + _id,null);
    }

    public Cursor fetch(){
        String[] columns = new String[]{dbHelper._ID,dbHelper.NAME,dbHelper.TEACHER};
        Cursor cursor = sqlDB.query(dbHelper.TABLE_NAME,columns,null,null,null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();
        return cursor;
    }
}
