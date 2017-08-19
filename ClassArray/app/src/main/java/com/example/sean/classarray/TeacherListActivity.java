package com.example.sean.classarray;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class TeacherListActivity extends AppCompatActivity {
    ListView listView;
    SimpleCursorAdapter simpleAdapter;
    private DBTManager dbtManager;
    final String[] from = new String[] {DBTHelper.NAME,DBTHelper.ADDRESS};
    final int[] to = new int[]{R.id.teacherNameView,R.id.teacherAddressView};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Teacher List");
        setContentView(R.layout.activity_teacher_list);

        listView = (ListView) findViewById(R.id.teacherListView);
        listView.setEmptyView(findViewById(R.id.noTeachers));

        dbtManager = new DBTManager(this);
        dbtManager.open();
        Cursor cursor = dbtManager.fetch();

        simpleAdapter = new SimpleCursorAdapter(this,R.layout.single_teacher,cursor,from,to,0);
        simpleAdapter.notifyDataSetChanged();
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TeacherListActivity.this,UpdateTeacher.class);
                startActivity(intent);
            }
        });
    }
}
