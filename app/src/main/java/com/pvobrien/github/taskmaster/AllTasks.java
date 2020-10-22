package com.pvobrien.github.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class AllTasks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mtIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(mtIntent, 0);
        return true;
    }
}