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
/*
public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "android_api";

    // Login table name
    private static final String TABLE_USER = "user";

    // Login Table Columns names
    private static final String KEY_TOKEN = "token";
    private static final String KEY_EMAIL = "login";
    private static final String KEY_UID = "password";

    //UserDetails table columns
    private static final String STATUS = "status";
    private static final String FIRST_NAME = "first_name";
    private static final String EMAIL = "user_email";
    private static final String LAST_NAME = "last_name";
    private static final String COUNTRY = "country";
    private static final String COUNTRY_ALPHA2 = "country_alpha2";
    private static final String ZIP_CODE = "zip";
    private static final String ADDRESS = "address";
    private static final String USER_ACC_STATUS = "user_status";
    //UserDetails table name
    private static final String USER_TABLE_NAME = "userInfo";

    public SQLiteHandler(Context context) {
        super (context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_EMAIL + " TEXT UNIQUE," + KEY_UID + " TEXT," + ")";
        db.execSQL(CREATE_LOGIN_TABLE);

        String CREATE_USER_DETAILS_TABLE = "CREATE TABLE " + USER_TABLE_NAME + "("
                + STATUS + " INTEGER," + FIRST_NAME + " TEXT," + EMAIL + " TEXT," + LAST_NAME
                + " TEXT," + COUNTRY + " TEXT," + COUNTRY + " TEXT," + COUNTRY_ALPHA2 + " TEXT,"
                + ZIP_CODE + " INTEGER," + ADDRESS + " TEXT," + USER_ACC_STATUS + " TEXT," + ")";
        db.execSQL(CREATE_USER_DETAILS_TABLE);


        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
    }


    public void addUser(String email, String uid) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, email); // Email
        values.put(KEY_UID, uid); // Password

        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    /**
     * Getting user data from database
     * */
 /*   public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + USER_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("status", cursor.getString(1));
            user.put("first_name", cursor.getString(2));
            user.put("user_email", cursor.getString(3));
            user.put("last_name", cursor.getString(4));
            user.put("country", cursor.getString(5));
            user.put("country_alpha2", cursor.getString(6));
            user.put("zip", cursor.getString(7));
            user.put("address", cursor.getString(8));
            user.put("user_acc_status", cursor.getString(9));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
  /*  public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }

}
*/


  //------------------------------------------------------ with old Server side




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;



public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "android_api";

    // Login table name
    private static final String TABLE_USER = "user";

    // Login Table Columns names
    //private static final String KEY_ID = "id";
    //private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "login";
    private static final String KEY_UID = "password";
    private static final String KEY_TOKEN = "Token";
    //private static final String KEY_CREATED_AT = "created_at";

    // Details table name
    private static final String TABLE_DETAIL = "detail";
    // Details table columns
    private static final String STATUS = "Status";
    private static final String FIRST_NAME = "FirstName";
    private static final String LAST_NAME = "LastName";
    private static final String EMAIL = "Email";
    private static final String COUNTRY= "Country";
    private static final String COUNTRYALPHA2= "CountryAlpha2";
    private static final String CITY= "City";
    private static final String ZIP = "Zip";
    private static final String ADDRESS = "Address";
    private static final String USERACCSTATUS = "UserAccStatus";
    private static final String SKYPENAME = "SkypeName";
    private static final String COUNTRY_NAME = "CountryName";
    private static final String PHONE_CODE = "Phonecode";
    private static final String PHONE = "Phone";
    private static final String DEVICEID = "DeviceIdentificator";


    public SQLiteHandler(Context context) {
        super (context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_EMAIL + " TEXT," + KEY_UID + " TEXT," + KEY_TOKEN + " TEXT" + ")";
        db.execSQL(CREATE_LOGIN_TABLE);

        String CREATE_DETAILS_TABLE = "CREATE TABLE " + TABLE_DETAIL + "(" + STATUS + " TEXT," +
                FIRST_NAME + " TEXT," + LAST_NAME + " TEXT," + EMAIL + " TEXT," + COUNTRY
                + " TEXT," + COUNTRYALPHA2 + " TEXT," + CITY + " TEXT," + ZIP + " TEXT," + ADDRESS
                + " TEXT," + USERACCSTATUS + " TEXT," + SKYPENAME + " TEXT," + COUNTRY_NAME + " TEXT," +
                PHONE_CODE + " TEXT," + PHONE + " TEXT," + DEVICEID + " TEXT" + ")";
        db.execSQL(CREATE_DETAILS_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addUser(String email, String uid, String token) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(KEY_NAME, name); // Name
        values.put(KEY_EMAIL, email); // Email
        values.put(KEY_UID, uid); // Email
        values.put(KEY_TOKEN,token); // Token
        //values.put(KEY_CREATED_AT, created_at); // Created At

        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    public void addDetails(String status,String firstName,String lastName,String email
            ,String country,String countryAlpha2,String city,String zip,String address,String userAccStatus
    ,String skypename,String phonecode,String phone,String countryname,String deviceid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(STATUS,status);
        contentValues.put(FIRST_NAME,firstName);
        contentValues.put(LAST_NAME,lastName);
        contentValues.put(EMAIL,email);
        contentValues.put(COUNTRY,country);
        contentValues.put(COUNTRYALPHA2,countryAlpha2);
        contentValues.put(CITY,city);
        contentValues.put(ZIP,zip);
        contentValues.put(ADDRESS,address);
        contentValues.put(USERACCSTATUS,userAccStatus);
        contentValues.put(SKYPENAME,skypename);
        contentValues.put(PHONE_CODE,phonecode);
        contentValues.put(PHONE,phone);
        contentValues.put(COUNTRY_NAME,countryname);
        contentValues.put(DEVICEID,deviceid);

        long id = db.insert(TABLE_DETAIL,null,contentValues);
        db.close();

        Log.d(TAG,"User Details added into sqlite: " + id);
    }

    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_DETAIL;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
            user.put("Status", cursor.getString(0));
            user.put("FirstName", cursor.getString(1));
            user.put("LastName", cursor.getString(2));
            user.put("Email", cursor.getString(3));
            user.put("Country", cursor.getString(4));
            user.put("CountryAlpha2", cursor.getString(5));
            user.put("City", cursor.getString(6));
            user.put("Zip", cursor.getString(7));
            user.put("Address", cursor.getString(8));
            user.put("UserAccStatus", cursor.getString(9));
            user.put("SkypeName",cursor.getString(10));
            user.put("CountryName",cursor.getString(11));
            user.put("Phonecode",cursor.getString(12));
            user.put("Phone",cursor.getString(13));
            user.put("DeviceIdentificator",cursor.getString(14));
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }

    public HashMap<String,String> getUser() {
        HashMap<String, String> user = new HashMap<String,String>();
        String selectQuery = "SELECT * FROM " + TABLE_USER;

        SQLiteDatabase db =  this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        user.put("login", cursor.getString(0));
        user.put("password", cursor.getString(1));
        user.put("Token", cursor.getString(2));
        cursor.close();
        db.close();
        Log.d(TAG,"Fetching user from sqlite: " + user.toString());

        return user;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }






}