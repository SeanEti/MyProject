package com.example.sean.jason;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Sean on 8/10/2017.
 */

public class Adapter extends ArrayAdapter {

    List list = new ArrayList<>();
    public Adapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public void add(Contact contact){
        super.add(contact);
        list.add(contact);
    }
    static class ContactHolder{
        TextView name,mail,age,phone;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ContactHolder contactHolder;
        if(view==null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.row_layout,parent,false);
            contactHolder = new ContactHolder();
            contactHolder.name = (TextView) view.findViewById(R.id.nameView);
            contactHolder.mail = (TextView) view.findViewById(R.id.mailView);
            contactHolder.phone = (TextView) view.findViewById(R.id.phoneView);
            contactHolder.age = (TextView) view.findViewById(R.id.ageView);
            view.setTag(contactHolder);
        }
        else {
            contactHolder = (ContactHolder) view.getTag();
        }

        Contact contact = (Contact) this.getItem(position);
        contactHolder.name.setText(contact.getName());
        contactHolder.phone.setText(String.valueOf(contact.getPhone()));
        contactHolder.age.setText(String.valueOf(contact.getAge()));
        contactHolder.mail.setText(contact.getMail());

        return view;
    }

    /*@NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view==null)
            view = LayoutInflater.from(getContext()).inflate(R.layout.listview,parent,false);

        JSONObject jsonObject = getItem(position);
        TextView name = (TextView) view.findViewById(R.id.nameView);
        TextView age = (TextView) view.findViewById(R.id.ageView);
        TextView phone = (TextView) view.findViewById(R.id.phoneView);
        TextView mail = (TextView) view.findViewById(R.id.mailView);

        try {
            name.setText(jsonObject.getString("name"));
            age.setText(jsonObject.getInt("age"));
            phone.setText(jsonObject.getInt("phone number"));
            mail.setText(jsonObject.getString("mail"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }*/
}
