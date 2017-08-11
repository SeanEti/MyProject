package com.example.sean.jason;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    String json_string;
    Button getJson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getJson = (Button) findViewById(R.id.getJSONButton);
        /*getJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new backgroundTask().execute();
            }
        });*/
        findViewById(R.id.parseJSONButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(json_string==null){
                    Toast.makeText(MainActivity.this, "First,get JSON data", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                    intent.putExtra("json_data",json_string);
                    startActivity(intent);
                }
            }
        });
    }
    public void doSomething(View view){
        new backgroundTask().execute();

    }


    public class backgroundTask extends AsyncTask{
        String JSONSTRING;
        String JSONUrl;
        URL UO;

        @Override
        protected void onPreExecute() {
            JSONUrl = "http://10.100.102.9/dataRead.php";

        }

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                UO = new URL(JSONUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) UO.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while((JSONSTRING=bufferedReader.readLine())!=null){
                    stringBuilder.append(JSONSTRING);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onProgressUpdate(Object[] values) {

            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Object o) {
            TextView jsonView = (TextView) findViewById(R.id.JSONView);
            jsonView.setText((String) o);
            json_string = (String) o;
        }


    }
}
