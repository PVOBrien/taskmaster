package com.pvobrien.github.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;

import java.util.ArrayList;

public class AllTasks extends AppCompatActivity implements TaskAdapter.OnInteractWithTasksToDoListener {

//    YourUniqueDatabase yourUniqueDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            Amplify.addPlugin(new AWSApiPlugin());  // this is provided by implementation 'com.amplifyframework:aws-api:1.4.1'
            Amplify.configure(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized Amplify");

        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }

//        yourUniqueDatabase = Room.databaseBuilder(getApplicationContext(), YourUniqueDatabase.class, "taskDatabase")
//                .fallbackToDestructiveMigration()
//                .allowMainThreadQueries()
//                .build();

//        ArrayList<Task> tasks = (ArrayList<Task>) yourUniqueDatabase.taskDao().getAllTasks();

//        RecyclerView recyclerView = findViewById(R.id.tasksRv);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new TaskAdapter(tasks, this));

//        Handler handler = new Handler(Looper.getMainLooper(),
//
//                new Handler.Callback() {
//                    @Override
//                    public boolean handleMessage(@NonNull Message message) {
//                        recyclerView.getAdapter().notifyDataSetChanged();
//                        return false;
//                    }
//                });
//
//        Amplify.API.query(
//                ModelQuery.list(Task.class),
//                response -> {
//                    for (Task task : response.getData()) {
//                        tasks.add(task);
//                    }
//                    handler.sendEmptyMessage(1);
//                    Log.i("amplify.queryItems", "Got this many: " + tasks.size());
//                },
//                error -> Log.i("Amplify.queryItems", "Did not receive tasks"));


        Intent intent = getIntent();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mtIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(mtIntent, 0);
        return true;
    }


    @Override
    public void tasksToDoListener(Task task) {
        Intent intent = new Intent(AllTasks.this, TaskDetail.class);
        intent.putExtra("taskTitle", task.getTaskTitle());
        intent.putExtra("taskDetails", task.getTaskDetails());
        intent.putExtra("taskState", task.getTaskStateOfDoing());
        this.startActivity(intent);
    }
}