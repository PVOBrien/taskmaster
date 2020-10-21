package com.pvobrien.github.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        TextView myTaskTitle = findViewById(R.id.myTasksTitle);
        String greeting = String.format("%s's tasks", preferences.getString("savedUsername", "My Tasks"));
        myTaskTitle.setText(greeting);
        SharedPreferences.Editor preferenceEditor = preferences.edit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    Button goToAddTask = MainActivity.this.findViewById(R.id.addTask);
    goToAddTask.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            System.out.println("head to the tasks...");
            Intent goToAddTasks = new Intent(MainActivity.this, AddTask.class);
            MainActivity.this.startActivity(goToAddTasks);
        }
    });

    Button goToAllTasks = MainActivity.this.findViewById(R.id.allTasks);
    goToAllTasks.setOnClickListener((view) -> {
        System.out.println("Seeing all the tasks now.");
        Intent goToAllTasksNow = new Intent (MainActivity.this, AllTasks.class);
        MainActivity.this.startActivity(goToAllTasksNow);
    });


    ImageButton goToSettings = MainActivity.this.findViewById(R.id.homeToSettingsButton);
    goToSettings.setOnClickListener((view) -> {
        System.out.println("heading to settings.");
        Intent goToSettingPage = new Intent (MainActivity.this, Settings.class);
        MainActivity.this.startActivity(goToSettingPage);
    });
    }
}