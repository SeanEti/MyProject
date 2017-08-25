package com.example.sean.registrationactivity_lesson15;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.sean.registrationactivity_lesson15.Helper.SQLiteHandler;
import com.example.sean.registrationactivity_lesson15.Helper.SessionManager;
import com.example.sean.registrationactivity_lesson15.activities.AccountActivity;
import com.example.sean.registrationactivity_lesson15.activities.TopUpActivity;

public class NavigationDrawer extends AppCompatActivity {
    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction fragment;
    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigation;
    SQLiteHandler db;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        db = new SQLiteHandler(getApplicationContext());
        sessionManager = new SessionManager(getApplicationContext());
        setSupportActionBar(toolbar);
        fragment = getSupportFragmentManager().beginTransaction();
        fragment.add(R.id.frame,new AccountActivity());
        fragment.commit();
        getSupportActionBar().setTitle("Account");

        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.drawer_open,R.string.drawer_close);
        navigation = (NavigationView) findViewById(R.id.navigationMenu);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.account_item:
                        fragment = getSupportFragmentManager().beginTransaction();
                        fragment.replace(R.id.frame,new AccountActivity());
                        fragment.commit();
                        getSupportActionBar().setTitle("Account");
                        item.setChecked(true);
                        drawer.closeDrawers();
                        break;
                    case R.id.topup_item:
                        fragment = getSupportFragmentManager().beginTransaction();
                        fragment.replace(R.id.frame,new TopUpActivity());
                        fragment.commit();
                        getSupportActionBar().setTitle("Top Up");
                        item.setChecked(true);
                        drawer.closeDrawers();
                        break;
                    case R.id.logout_item:
                        sessionManager.SetLogin(false);
                        db.deleteUsers();
                        Intent intent = new Intent(NavigationDrawer.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        actionBarDrawerToggle.syncState();
        super.onPostCreate(savedInstanceState);
    }
}
