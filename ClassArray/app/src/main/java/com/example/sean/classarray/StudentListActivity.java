package com.example.sean.classarray;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class StudentListActivity extends AppCompatActivity {
    ListView listView;
    SimpleCursorAdapter adapter;
    TextView name,teacher;
    private DataBaseManager dataBaseManager;
    final String[] from = new String[] {DataBaseHelper.NAME,DataBaseHelper.TEACHER};
    final int[] to = new int[]{R.id.studentName,R.id.teacherView};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Student List");
        setContentView(R.layout.activity_student_list);

        listView = (ListView) findViewById(R.id.studentListView);
        listView.setEmptyView(findViewById(R.id.noStudentsText));

        dataBaseManager = new DataBaseManager(this);
        dataBaseManager.open();
        Cursor cursor = dataBaseManager.fetch();

        adapter = new SimpleCursorAdapter(this,R.layout.single_student,cursor,from,to,0);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dataBaseManager.delete(id);
            }
        });
    }
}
