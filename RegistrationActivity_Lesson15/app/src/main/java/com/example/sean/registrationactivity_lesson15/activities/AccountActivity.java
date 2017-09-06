package com.example.sean.registrationactivity_lesson15.activities;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sean.registrationactivity_lesson15.Helper.SQLiteHandler;
import com.example.sean.registrationactivity_lesson15.R;

public class AccountActivity extends Fragment {
    TextView cardTypeView,balanceView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_account,container,false);
        cardTypeView = (TextView) view.findViewById(R.id.cardTypeView);
        balanceView = (TextView) view.findViewById(R.id.balanceView);
        SQLiteHandler sqlite = new SQLiteHandler(getActivity());
        cardTypeView.setText(sqlite.getCardInfo("1").get("CardType"));
        balanceView.setText(sqlite.getCardInfo("1").get("Balance"));

        return view;
    }
}
