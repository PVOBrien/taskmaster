package com.pvobrien.github.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

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

    }
}