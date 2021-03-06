package com.pvobrien.github.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import com.amplifyframework.datastore.generated.model.Task;
import java.util.ArrayList;

public class RecyclerViewGeneric extends AppCompatActivity implements TaskAdapter.OnInteractWithTasksToDoListener {

    androidx.room.Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_generic);

        ArrayList<Task> tasks = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.tasksRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TaskAdapter(tasks, this));
    }

    @Override
    public void tasksToDoListener(Task taskLocal) {
        Intent intent = new Intent(RecyclerViewGeneric.this, TaskDetail.class);
        intent.putExtra("taskTitle", taskLocal.getTaskTitle());
        intent.putExtra("taskDetails", taskLocal.getTaskDetails());
        intent.putExtra("taskState", taskLocal.getTaskStateOfDoing());
        this.startActivity(intent);
    }
}