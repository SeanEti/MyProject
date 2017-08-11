package com.example.sean.jason;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity {
    String json_string;
    Adapter adapter;
    ListView listView;
    JSONObject jsonObject;
    JSONArray jsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        listView = (ListView) findViewById(R.id.listView);
        adapter = new Adapter(this,R.layout.row_layout);
        listView.setAdapter(adapter);

        Bundle b = getIntent().getExtras();
        json_string = b.getString("json_data");

        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("user");
            int count=0;
            String name,mail;
            int phone,age;
            while(count<jsonArray.length()){
                JSONObject jsonObject1 = jsonArray.getJSONObject(count);
                name = jsonObject1.getString("name");
                mail = jsonObject1.getString("mail");
                phone = jsonObject1.getInt("phone number");
                age = jsonObject1.getInt("age");
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
