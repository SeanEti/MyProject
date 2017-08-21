package com.example.sean.registrationactivity_lesson15;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sean.registrationactivity_lesson15.App.AppConfig;
import com.example.sean.registrationactivity_lesson15.App.AppController;
import com.example.sean.registrationactivity_lesson15.Helper.SQLiteHandler;
import com.example.sean.registrationactivity_lesson15.Helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    Button register,backToLogin;
    EditText password,username,email;
    ProgressDialog progressDialog;
    SQLiteHandler db;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = (Button) findViewById(R.id.registerButton);
        backToLogin = (Button) findViewById(R.id.returnToLoginButton);
        password = (EditText) findViewById(R.id.registerPassword);
        username = (EditText) findViewById(R.id.registerUsername);
        email = (EditText) findViewById(R.id.registerMail);
        sessionManager = new SessionManager(getApplicationContext());
        db = new SQLiteHandler(getApplicationContext());
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        if(sessionManager.IsLoggedIn()){
            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString().trim()
                        ,mail = email.getText().toString().trim()
                        ,pass = password.getText().toString().trim();
                if(!name.isEmpty()&&!mail.isEmpty()&&!pass.isEmpty()){
                    registerUser(name,mail,pass);
                }else{
                    Toast.makeText(RegisterActivity.this, "Please don't leave blank areas", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void registerUser(final String name,final String mail,final String password){
        final String TAG_string_req = "login_req";
        progressDialog.setMessage("User registered!");
        showDialog();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "register response: " + response.toString());
                hideDialog();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean error = jsonObject.getBoolean("error");
                    if (!error) {
                        JSONObject newUser = new JSONObject("user");
                        String name = newUser.getString("name"), mail = newUser.getString("mail"), password = newUser.getString("password"), created_time = newUser.getString("ct");
                        db.AddUser(name, password, mail, created_time);
                        Toast.makeText(RegisterActivity.this, "User successfully registered", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        String error_message = jsonObject.getString("error_message");
                        Toast.makeText(RegisterActivity.this, error_message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,"registration error "+error.getMessage());
                Toast.makeText(RegisterActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                hideDialog();
            }
        }
        ){
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String,String>();
                params.put("name",name);
                params.put("mail",mail);
                params.put("password",password);
                return params;

            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest,TAG_string_req);


    }
    private void showDialog(){
        if(!progressDialog.isShowing())
            progressDialog.show();
    }
    private void hideDialog(){
        if(progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
