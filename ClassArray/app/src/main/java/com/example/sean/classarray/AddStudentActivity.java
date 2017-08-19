package com.example.sean.classarray;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddStudentActivity extends Activity {
    EditText nameEditS,addressEditS;
    Button addBtn,cancelBtn;
    private DataBaseManager dbManager;
    String[] teachers = new String[]{"David","Bella","Melanie"};
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Student");
        setContentView(R.layout.activity_add_student);
        nameEditS = (EditText) findViewById(R.id.nameEditS);
        addressEditS = (EditText) findViewById(R.id.addEditS);
        addBtn = (Button) findViewById(R.id.addStudentBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        spinner = (Spinner) findViewById(R.id.spinner);


        dbManager = new DataBaseManager(this);
        dbManager.open();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditS.getText().toString()
                        ,address = addressEditS.getText().toString()
                        ,teacher = teachers[0];
                dbManager.insert(name,address,teacher);
                finish();
            }
        });


        spinner.setAdapter(new CustomAdapter(R.layout.simple_spinner_item,R.layout.support_simple_spinner_dropdown_item,teacher));
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
