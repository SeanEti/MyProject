package com.example.sean.registrationactivity_lesson15;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
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

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity {
    private static final String TAG = RegisterActivity.class.getSimpleName();

    Button login,signup;
    EditText username,password;
    SQLiteHandler sqLiteHandler;
    SessionManager sessionManager;
    HashMap<String,String> user;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.loginButton);
        signup = (Button) findViewById(R.id.signupButton);
        username = (EditText) findViewById(R.id.enterUsername);
        password = (EditText) findViewById(R.id.enterPassword);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        sqLiteHandler = new SQLiteHandler(getApplicationContext());
        sessionManager = new SessionManager(getApplicationContext());

        if(sessionManager.IsLoggedIn()){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username1 = username.getText().toString().trim()
                        ,passoword1 = password.getText().toString().trim();
                if(username1.isEmpty()||passoword1.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Username or password are missing", Toast.LENGTH_SHORT).show();
                }else {
                    checkLogin(username1, passoword1);
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public void checkLogin(final String mail, final String password){//homework!!!
        /*user = sqLiteHandler.GetUser();
        if(mail.equals(user.get("mail"))&&password.equals(user.get("uid"))){
            sessionManager.SetLogin(true);
        }else
            Toast.makeText(this, "Username or password are incorrect", Toast.LENGTH_SHORT).show();
            */
        final String TAG_string_request= "req_login";
        progressDialog.setMessage("You have logged in");
        showDialog();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login response: " + response.toString());
                hideDialog();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean error = jsonObject.getBoolean("error");
                    if (!error) {
                        sessionManager.SetLogin(true);
                        String uid = jsonObject.getString("uid");
                        JSONObject user = new JSONObject("user");
                        String name = user.getString("name"), mail = user.getString("mail"), created_time = user.getString("ct"), pass = user.getString("password");
                        sqLiteHandler.AddUser(name, pass, mail, created_time);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        String error_message = jsonObject.getString("error message");
                        Toast.makeText(LoginActivity.this, error_message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "Json error " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,"Login error: " + error.getMessage());
                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                hideDialog();

            }
        }){
            protected Map<String,String> getParams(){
                Map<String , String> params = new HashMap<String,String>();
                params.put("mail",mail);
                params.put("password",password);

                return params;
            }


        };

        AppController.getInstance().addToRequestQueue(stringRequest,TAG_string_request);

    }

    private void showDialog(){
        if(!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideDialog(){
        if (!progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
