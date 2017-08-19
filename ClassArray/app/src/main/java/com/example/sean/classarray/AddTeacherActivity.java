package com.example.sean.classarray;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddTeacherActivity extends Activity {
    Button addBTN,cancelBTN;
    EditText nameT,addressT;
    private DBTManager dbtManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Teacher");
        setContentView(R.layout.activity_add_teacher);
        addBTN = (Button) findViewById(R.id.addTeacherBtn);
        cancelBTN = (Button) findViewById(R.id.cancelButton);
        nameT = (EditText) findViewById(R.id.nameEditT);
        addressT = (EditText) findViewById(R.id.addEditT);

        dbtManager = new DBTManager(this);
        dbtManager.open();

        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameT.getText().toString()
                        ,address = addressT.getText().toString();
                dbtManager.insert(name,address);
                finish();
            }
        });

        cancelBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
