package com.pvobrien.github.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        System.out.println(intent.getExtras().getString("taskTitle"));
        System.out.println(intent.getExtras().getString("taskDetails"));
        System.out.println(intent.getExtras().getString("taskState"));

        TextView taskTitleTv = TaskDetail.this.findViewById(R.id.taskDetailsTitle);
        TextView taskDetailsTv = TaskDetail.this.findViewById(R.id.taskDescriptionTv);
        TextView taskStateTv = TaskDetail.this.findViewById(R.id.statusTv);

        taskTitleTv.setText(intent.getExtras().getString("taskTitle"));
        taskDetailsTv.setText(intent.getExtras().getString("taskDetails"));
        taskStateTv.setText(intent.getExtras().getString("taskState"));

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mtIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(mtIntent, 0);
        return true;
    }
}