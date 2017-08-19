package com.example.sean.classarray;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sean on 8/17/2017.
 */

class CustomAdapter extends ArrayAdapter<String> {

    private final List objects;
    LayoutInflater inflater;



    public CustomAdapter(@NonNull Context context, @LayoutRes int resource, int dropDownViewResource, @NonNull List objects) {
        super(context, resource, objects);
        this.setDropDownViewResource(dropDownViewResource);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.objects = objects;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        r
        eturn getCustomView(position, convertView, parent);
    }

    public void getCustomView(int position,View convertView,ViewGroup parent){
        final View row = inflater.inflate(android.R.layout.simple_spinner_item,parent,false);
        // TextView label = row.findViewById(R.id.t)

       // final TeacherDetails teacher = this.objects.get(position);

        )
    }
}
