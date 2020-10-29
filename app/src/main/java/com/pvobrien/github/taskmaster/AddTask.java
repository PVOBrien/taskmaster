package com.pvobrien.github.taskmaster;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;

public class AddTask extends AppCompatActivity {

    YourUniqueDatabase yourUniqueDatabase; // this is looking specifically for YOUR yourUniqueDatabase class name/potato

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        yourUniqueDatabase = Room.databaseBuilder(getApplicationContext(), YourUniqueDatabase.class, "taskDatabase") // this YourUniqueDatabase.class is looking for YOUR yourUniqueDatabase class name/potato.
                    .allowMainThreadQueries()
                    .build();

        TextView taskTitleTv = AddTask.this.findViewById(R.id.taskName);
        TextView taskDetailsTv  = AddTask.this.findViewById(R.id.taskDetails);
        TextView taskStatusTv = AddTask.this.findViewById(R.id.taskStatusTv);

        Context context = getApplicationContext();
        CharSequence text = "TaskLocal Entered";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);

        TaskLocal taskLocalToAdd = new TaskLocal(taskTitleTv.getText().toString(), taskDetailsTv.getText().toString(), taskStatusTv.getText().toString());

        Button addButton = AddTask.this.findViewById(R.id.addTaskButton);
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                TextView taskTitleTv = AddTask.this.findViewById(R.id.taskName);
                TextView taskDetailsTv  = AddTask.this.findViewById(R.id.taskDetails);
                TextView taskStatusTv = AddTask.this.findViewById(R.id.taskStatusTv);

                TaskLocal taskLocalToAdd = new TaskLocal(taskTitleTv.getText().toString(), taskDetailsTv.getText().toString(), taskStatusTv.getText().toString());

                // CREATE TASK via TaskLocal.builder()...

            com.amplifyframework.datastore.generated.model.Task newTask = com.amplifyframework.datastore.generated.model.Task.builder()
                    .taskDetails(taskDetailsTv.getText().toString())
                    .taskStateOfDoing(taskStatusTv.getText().toString())
                    .taskTitle(taskTitleTv.getText().toString())
                    .build();

            Amplify.API.mutate( // https://docs.amplify.aws/lib/graphqlapi/mutate-data/q/platform/android
                    ModelMutation.create(newTask),
                    response -> Log.i("Amplify", "success!"),
                    error -> Log.e("Amplify", "Failure", error)); // must be completely built and entirely correct in order to not be grumpy. Ie it's red from the get-go, and only after the ; is in place with all details in order does it quiet down.



//                yourUniqueDatabase.taskDao().saveTheThing(taskLocalToAdd);

                System.out.println(String.format("You have a new task called %s and you'll be %s all days long", taskTitleTv.getText().toString(), taskDetailsTv.getText().toString()));

                toast.show();

//                finish(); // this "closes out" the activity, sends us back to where we are.
                onBackPressed(); // this basically does the same thing.

//                Intent addTaskToAllTasks = new Intent(AddTask.this, MainActivity.class);
//                AddTask.this.startActivity(addTaskToAllTasks);

            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mtIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(mtIntent, 0);
        return true;
    }
}
