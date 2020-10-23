package com.pvobrien.github.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class RecyclerViewGeneric extends AppCompatActivity implements TaskAdapter.OnInteractWithTasksToDoListener {

    androidx.room.Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_generic);

        Task taskToDb = new Task("into thedb", "do it!", "working on it");

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
    public void tasksToDoListener(Task task) {
        Intent intent = new Intent(RecyclerViewGeneric.this, TaskDetail.class);
        intent.putExtra("taskTitle", task.taskTitle);
        intent.putExtra("taskDetails", task.taskDetails);
        intent.putExtra("taskState", task.taskStateOfDoing);
        this.startActivity(intent);
    }
}