package com.example.sean.classarray;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateTeacher extends Activity {
    EditText name,address;
    Button del,update;
    long _id;
    private DBTManager dbtManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Update Teacher");
        setContentView(R.layout.activity_update_teacher);
        name = (EditText) findViewById(R.id.nameUpdateT);
        address = (EditText) findViewById(R.id.addUpdateT);
        del = (Button) findViewById(R.id.deleteButton);
        update = (Button) findViewById(R.id.updateTeacherBtn);

        dbtManager = new DBTManager(this);
        dbtManager.open();

        Intent intent = getIntent();
        String id = intent.getStringExtra("_id")
                ,name2 = intent.getStringExtra("name")
                ,address2 = intent.getStringExtra("address");
        _id = Long.parseLong(id);
        name.setText(name2);
        address.setText(address2);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name.getText().toString()
                        ,address1 = address.getText().toString();
                dbtManager.update(_id,name1,address1);
                finish();
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbtManager.delete(_id);
                finish();
            }
        });
    }
}
