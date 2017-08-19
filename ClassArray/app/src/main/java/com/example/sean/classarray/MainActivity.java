package com.example.sean.classarray;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void list(View view){
        switch(view.getId()){
            case R.id.studentList:
                Intent intent = new Intent(MainActivity.this,StudentListActivity.class);
                startActivity(intent);
                break;
            case R.id.teacherList:
                Intent intent1 = new Intent(MainActivity.this,TeacherListActivity.class);
                startActivity(intent1);
                break;
        }
    }

    public void add(View view){
        switch(view.getId()){
            case R.id.addStudent:
                Intent intent = new Intent(MainActivity.this,AddStudentActivity.class);
                startActivity(intent);
                break;
            case R.id.addTeacher:
                Intent intent1 = new Intent(MainActivity.this,AddTeacherActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
