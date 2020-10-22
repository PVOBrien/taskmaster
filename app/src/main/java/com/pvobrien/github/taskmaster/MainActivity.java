package com.pvobrien.github.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnInteractWithTasksToDoListener {

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        TextView myTaskTitle = findViewById(R.id.myTasksTitle);
        String greeting = String.format("%s's tasks", preferences.getString("savedUsername", "My Tasks"));
        myTaskTitle.setText(greeting);
        SharedPreferences.Editor preferenceEditor = preferences.edit();

        ArrayList<Task> tasks = new ArrayList<>();

        Task thingOne = new Task("a thing, one", "do the one thing", "In Progress");
        Task thingTwo = new Task("Second thing, one", "get to #2", "Completed");
        Task thingThree = new Task("That Third", "3 is waiting 4 u", "Not Started");

        tasks.add(thingOne);
        tasks.add(thingTwo);
        tasks.add(thingThree);

        RecyclerView recyclerView = findViewById(R.id.tasksRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TaskAdapter(tasks, this));
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
            Intent goToAllTasksNow = new Intent(MainActivity.this, recyclerViewGeneric.class);
            MainActivity.this.startActivity(goToAllTasksNow);
        });


        ImageButton goToSettings = MainActivity.this.findViewById(R.id.homeToSettingsButton);
        goToSettings.setOnClickListener((view) -> {
            System.out.println("heading to settings.");
            Intent goToSettingPage = new Intent(MainActivity.this, Settings.class);
            MainActivity.this.startActivity(goToSettingPage);
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor preferenceEditor = preferences.edit();

        Button selectTaskOneButton = MainActivity.this.findViewById(R.id.taskOneButton);
        selectTaskOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TaskDetail.class);
                Button taskOneButton = findViewById(R.id.taskOneButton);
                i.putExtra("taskTitle", taskOneButton.getText().toString());
                i.putExtra("body", taskOneButton.getText().toString());
                i.putExtra("state",taskOneButton.getText().toString());
                MainActivity.this.startActivity(i);
            }
        });

        Button selectTaskTwoButton = MainActivity.this.findViewById(R.id.taskTwoButton);
        selectTaskTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TaskDetail.class);
                Button taskTwoButton = findViewById(R.id.taskTwoButton);
                i.putExtra("taskTitle", taskTwoButton.getText().toString());
                i.putExtra("body", taskTwoButton.getText().toString());
                i.putExtra("state",taskTwoButton.getText().toString());
                MainActivity.this.startActivity(i);
            }
        });

        Button selectTaskThreeButton = MainActivity.this.findViewById(R.id.taskThreeButton);
        selectTaskThreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TaskDetail.class);
                Button taskThreeButton = findViewById(R.id.taskThreeButton);
                i.putExtra("taskTitle", taskThreeButton.getText().toString());
                i.putExtra("body", taskThreeButton.getText().toString());
                i.putExtra("state",taskThreeButton.getText().toString());
                MainActivity.this.startActivity(i);
            }
        });
    }

    @Override
    public void tasksToDoListener(Task task) {
        Intent intent = new Intent(MainActivity.this, TaskDetail.class);
        intent.putExtra("taskTitle", task.taskTitle);
        intent.putExtra("taskDetails", task.taskDetails);
        intent.putExtra("taskState", task.taskStateOfDoing);
        this.startActivity(intent);
    }
}