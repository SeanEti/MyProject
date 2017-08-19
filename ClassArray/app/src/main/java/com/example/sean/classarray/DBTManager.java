package com.example.sean.classarray;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Sean on 8/17/2017.
 */

public class DBTManager {
    private DBTHelper dbTHelper;
    private SQLiteDatabase sqlDB;
    private Context context;

    public DBTManager(Context c){context = c;}

    public DBTManager open() throws SQLiteAbortException {
        dbTHelper = new DBTHelper(context);
        sqlDB = dbTHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbTHelper.close();
    }

    public void insert(String name,String address){
        ContentValues values = new ContentValues();
        values.put(dbTHelper.NAME,name);
        values.put(dbTHelper.ADDRESS,address);
        sqlDB.insert(dbTHelper.TABLE_NAME,null,values);
    }

    public void delete(long _id){
        sqlDB.delete(dbTHelper.TABLE_NAME,dbTHelper._ID + " = " + _id,null);
    }

    public Cursor fetch(){
        String[] columns = new String[]{dbTHelper._ID,dbTHelper.NAME,dbTHelper.ADDRESS};
        Cursor cursor = sqlDB.query(dbTHelper.TABLE_NAME,columns,null,null,null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();
        return cursor;
    }

    public int update(long _id,String name,String address){
        ContentValues values = new ContentValues();
        values.put(DBTHelper.NAME,name);
        values.put(DBTHelper.ADDRESS,address);
        int i = sqlDB.update(DBTHelper.TABLE_NAME,values,DBTHelper._ID + " = " + _id,null);
        return i;
    }
}
