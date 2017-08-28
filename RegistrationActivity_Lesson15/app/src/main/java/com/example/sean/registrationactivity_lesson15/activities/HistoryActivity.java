package com.example.sean.registrationactivity_lesson15.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.sean.registrationactivity_lesson15.R;

public class HistoryActivity extends Fragment {

    TextView fromDate;
    DatePicker calendar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_history, container, false);
        fromDate = (TextView) view.findViewById(R.id.fromDateView);
        calendar = (DatePicker) view.findViewById(R.id.calendar);


        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater factory = LayoutInflater.from(getActivity());
                final View textEntryView = factory.inflate(R.layout.calendar_view, null);
                final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle("Pick a date")
                        .setView(textEntryView)
                        .setPositiveButton("save",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        int day = calendar.getDayOfMonth()
                                                ,month = calendar.getMonth()
                                                ,year = calendar.getYear();
                                        fromDate.setText(day+"/"+month+"/"+year);
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int whichButton) {
                                    }
                                });
                alert.show();
                //openDialog();
            }
        });

        return view;
    }


    public void openDialog() {
        final Dialog dialog = new Dialog(getActivity()); // Context, this, etc.
        dialog.setContentView(R.layout.calendar_view);
        dialog.setTitle("Pick a date");
        dialog.show();
    }
}
