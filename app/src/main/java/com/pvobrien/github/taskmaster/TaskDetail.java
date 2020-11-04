package com.pvobrien.github.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;

import org.w3c.dom.Text;

import java.io.File;

public class TaskDetail extends AppCompatActivity {

//    String filetoShow;

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

        Log.i("System.extras", "Here's the fileKey" + intent.getExtras().getString("fileKey"));

        if (intent.getExtras().containsKey("fileKey")) {
            downloadFile(intent.getExtras().getString("fileKey"));
        }

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mtIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(mtIntent, 0);
        return true;
    }

    public void downloadFile(String fileKey) { // fileKey will be coming from intent atm }
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

}