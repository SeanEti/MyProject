package com.example.sean.registrationactivity_lesson15;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
    TextView emailView,passView,forgotPassView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.loginButton);
        signup = (Button) findViewById(R.id.signupButton);
        username = (EditText) findViewById(R.id.enterUsername);
        password = (EditText) findViewById(R.id.enterPassword);
        forgotPassView = (TextView) findViewById(R.id.forgotPass);
        emailView = (TextView) findViewById(R.id.mailView);
        passView = (TextView) findViewById(R.id.passView);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        sqLiteHandler = new SQLiteHandler(getApplicationContext());
        sessionManager = new SessionManager(getApplicationContext());

        if(sessionManager.IsLoggedIn()){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        forgotPassView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });

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



      ///////TEST
    /**
     * function to verify login details in mysql db
     * */
    private void checkLogin(final String email, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        progressDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // user successfully logged in
                        // Create login session
                        sessionManager.SetLogin(true);

                        // Now store the user in SQLite
                        String uid = jObj.getString("uid");
                        JSONObject user = jObj.getJSONObject("user");
                        String name = user.getString("name");
                        String email = user.getString("email");
                        String created_at = user.getString("created_at");

                        // Inserting row in users table
                        sqLiteHandler.addUser(name, email, uid, created_at);

                        // Launch main activity
                        Intent intent = new Intent(LoginActivity.this,
                                NavigationDrawer.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }

        };





        AppController.getInstance().addToRequestQueue(strReq,tag_string_req);

    }
    public void language(View view){
        switch(view.getId()){
            case R.id.ukranianLangBtn:
                username.setHint(R.string.enter_email_ukr);
                password.setHint(R.string.enter_password_ukr);
                forgotPassView.setText(R.string.forgot_password_ukr);
                passView.setText(R.string.password_view_ukr);
                login.setText(R.string.login_button_text_ukr);
                signup.setText(R.string.join_button_text_ukr);
                break;
            case R.id.russianLangBtn:
                username.setHint(R.string.enter_email_ru);
                password.setHint(R.string.enter_password_ru);
                forgotPassView.setText(R.string.forgot_password_ru);
                passView.setText(R.string.password_view_ru);
                login.setText(R.string.login_button_text_ru);
                signup.setText(R.string.join_button_text_ru);
                break;
            case R.id.englishLangBtn:
                username.setHint(R.string.enter_email);
                password.setHint(R.string.enter_password);
                forgotPassView.setText(R.string.forgot_password);
                passView.setText(R.string.password_view);
                login.setText(R.string.login_button_text);
                signup.setText(R.string.join_button_text);
                break;
        }
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
