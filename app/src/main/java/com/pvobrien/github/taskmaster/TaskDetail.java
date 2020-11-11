package com.pvobrien.github.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.analytics.AnalyticsEvent;
import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TaskDetail extends AppCompatActivity {

//    String filetoShow;
    String taskIdOnPage;
    GraphQLResponse<Task> taskOfPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        taskIdOnPage = getIntent().getExtras().getString("taskId");

        Amplify.API.query(
                ModelQuery.get(Task.class, taskIdOnPage),
                response -> {
                    taskOfPage = response;
                    Log.i("Amplify.queryItems", "This task is now available: " + taskOfPage);
                },
                error -> Log.i("Amplify.queryItems", "Did not receive any task via ID")
        );

        AnalyticsEvent onTaskPage = AnalyticsEvent.builder() // the basic pinpoint event builder. build'em as you need them,
                .name("onTaskPage")
                .addProperty("time", Long.toString(new Date().getTime())) // using java.util for Date(), not sql
                .addProperty("On The Page: ", "Task")
                .build();
        Amplify.Analytics.recordEvent(onTaskPage);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        System.out.println(intent.getExtras().getString("taskTitle")); // lol ain't no such thing as sout in Android Studio. This is dummy/dead code

        TextView taskTitleTv = TaskDetail.this.findViewById(R.id.taskDetailsTitle);
        TextView taskDetailsTv = TaskDetail.this.findViewById(R.id.taskDescriptionTv);
        TextView taskStateTv = TaskDetail.this.findViewById(R.id.statusTv);
        Button deleteButton = findViewById(R.id.deleteButton);
        TextView taskLocationTv = TaskDetail.this.findViewById(R.id.locationAdd);

        taskLocationTv.setText(intent.getExtras().getString("taskLocation", "No Address"));

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("System.Task", "Here's the graphQL Task: " + taskOfPage);

                deleteTask(taskOfPage);

                startActivity(new Intent(TaskDetail.this, MainActivity.class));
            }
        });

        taskTitleTv.setText(intent.getExtras().getString("taskTitle"));
        taskDetailsTv.setText(intent.getExtras().getString("taskDetails"));
        taskStateTv.setText(intent.getExtras().getString("taskState"));

        Log.i("System.extras", "Here's the fileKey: " + intent.getExtras().getString("fileKey"));
        Log.i("System.extras", "Here's the task's ID: " + intent.getExtras().getString("taskId"));
        taskIdOnPage = intent.getExtras().getString("taskId");

        if (intent.getExtras().containsKey("fileKey")) {
            downloadFile(intent.getExtras().getString("fileKey"));
        }

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mtIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(mtIntent, 0);
        return true;
    }


    public void downloadFile(String fileKey) { // fileKey will be coming from intent atm } // code direction from Jack Nelson https://github.com/jnelsonjava/taskmaster/blob/main/app/src/main/java/com/jnelsonjava/taskmaster/AddTask.java
        Amplify.Storage.downloadFile(
                fileKey,
                new File(getApplicationContext().getFilesDir() + "/" + fileKey + ".txt"),
                result -> {
                    Log.i("Amplify.s3dl", "Successful download: " + result.getFile().getName());
                    ImageView image = findViewById(R.id.attachedPic);
                    image.setImageBitmap(BitmapFactory.decodeFile(result.getFile().getPath()));
                },
                error -> Log.e("Amplify.s3down", "Download Fail", error)
        );
    }


//    TODO: create DELETE option via transcribing code from JS to java from here https://aws.amazon.com/getting-started/hands-on/build-android-app-amplify/module-four/

    public void deleteTask(GraphQLResponse<Task> task){
        if (task == null) {
            return;
        }

        Amplify.API.mutate(
                ModelMutation.delete(task.getData()), // why do I have to "getData"? Due to abstraction?
                response -> Log.i("Amplify.delete", "Successfully deleted:" + task.getData().getTaskTitle()),
                error -> Log.e ("Amplify.delete", "Failure to delete")
        );
    }

}