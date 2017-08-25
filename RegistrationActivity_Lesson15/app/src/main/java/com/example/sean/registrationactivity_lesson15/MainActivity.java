package com.example.sean.registrationactivity_lesson15;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sean.registrationactivity_lesson15.Helper.SQLiteHandler;
import com.example.sean.registrationactivity_lesson15.Helper.SessionManager;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    Button logout;
    TextView name,mail;
    SQLiteHandler db;
    SessionManager sessionManager;
    HashMap<String,String> user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logout = (Button) findViewById(R.id.logoutButton);
        name = (TextView) findViewById(R.id.username);
        mail = (TextView) findViewById(R.id.userMail);

        db = new SQLiteHandler(getApplicationContext());
        sessionManager = new SessionManager(getApplicationContext());

        if(!sessionManager.IsLoggedIn())
            logoutUser();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

        user = db.getUserDetails();
        String name1 = user.get("name")
                ,mail1 = user.get("email");
        name.setText(name1);
        mail.setText(mail1);
    }
    private void logoutUser(){
        sessionManager.SetLogin(false);
        db.deleteUsers();
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
