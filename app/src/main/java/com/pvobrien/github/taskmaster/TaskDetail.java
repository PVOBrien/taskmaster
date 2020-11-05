package com.pvobrien.github.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;

import java.io.File;

public class TaskDetail extends AppCompatActivity {

//    String filetoShow;
    String taskIdOnPage;
    GraphQLResponse<Task> taskOfPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        System.out.println(intent.getExtras().getString("taskTitle")); // lol ain't no such thing as sout in Android Studio. This is dummy/dead code

        TextView taskTitleTv = TaskDetail.this.findViewById(R.id.taskDetailsTitle);
        TextView taskDetailsTv = TaskDetail.this.findViewById(R.id.taskDescriptionTv);
        TextView taskStateTv = TaskDetail.this.findViewById(R.id.statusTv);

        taskTitleTv.setText(intent.getExtras().getString("taskTitle"));
        taskDetailsTv.setText(intent.getExtras().getString("taskDetails"));
        taskStateTv.setText(intent.getExtras().getString("taskState"));

        Log.i("System.extras", "Here's the fileKey: " + intent.getExtras().getString("fileKey"));
        Log.i("System.extras", "Here's the task's ID: " + intent.getExtras().getString("taskId"));
        taskIdOnPage = intent.getExtras().getString("taskId");

        if (intent.getExtras().containsKey("fileKey")) {
            downloadFile(intent.getExtras().getString("fileKey"));
        }

        Amplify.API.query(
                ModelQuery.get(Task.class, taskIdOnPage),
                response -> {
                    taskOfPage = response;
                },
                error -> Log.i("Amplify.queryItems", "Did not receive any task via ID")
        );

//        Amplify.API.query(
//                ModelQuery.get(Task.class, taskIdOnPage),
//                response -> {
//                    taskOfPage = response;
//                },
//                error -> Log.i("Amplify.queryItems", "Did not receive any task via ID")
//        );
    }




    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mtIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(mtIntent, 0);
        return true;
    }

//    public boolean getSpecificTask(String taskId) {

//    }


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

    public void deleteTask(Task task){
        if (task == null) {
            return;
        }

        Amplify.API.mutate(
                ModelMutation.delete(task),
                response -> Log.i("Amplify.delete", "Successfully deleted:" + task.getTaskTitle()),
                error -> Log.e ("Amplify.delete", "Failure to delete")
        );
    }

}