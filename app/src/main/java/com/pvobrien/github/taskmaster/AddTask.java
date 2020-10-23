package com.pvobrien.github.taskmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class AddTask extends AppCompatActivity {

    YourUniqueDatabase yourUniqueDatabase; // this is looking specifically for YOUR yourUniqueDatabase class name/potato

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);

        yourUniqueDatabase = Room.databaseBuilder(getApplicationContext(), YourUniqueDatabase.class, "taskDatabase") // this YourUniqueDatabase.class is looking for YOUR yourUniqueDatabase class name/potato.
                    .allowMainThreadQueries()
                    .build();

        TextView taskTitleTv = AddTask.this.findViewById(R.id.taskName);
        TextView taskDetailsTv  = AddTask.this.findViewById(R.id.taskDetails);
        TextView taskStatusTv = AddTask.this.findViewById(R.id.taskStatusTv);

        Task taskToAdd = new Task(taskTitleTv.getText().toString(), taskDetailsTv.getText().toString(), taskStatusTv.getText().toString());

        Button addButton = AddTask.this.findViewById(R.id.addTaskButton);
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                TextView taskTitleTv = AddTask.this.findViewById(R.id.taskName);
                TextView taskDetailsTv  = AddTask.this.findViewById(R.id.taskDetails);
                TextView taskStatusTv = AddTask.this.findViewById(R.id.taskStatusTv);

                Task taskToAdd = new Task(taskTitleTv.getText().toString(), taskDetailsTv.getText().toString(), taskStatusTv.getText().toString());

                yourUniqueDatabase.taskDao().saveTheThing(taskToAdd);

//                Button addButton = AddTask.this.findViewById(R.id.addTaskButton);

                System.out.println(String.format("You have a new task called %s and you'll be %s all days long", taskTitleTv.getText().toString(), taskDetailsTv.getText().toString()));
//
//                Toast.makeText(getApplicationContext(), "Submitted!", Toast.LENGTH_LONG).show();
//
                Intent addTaskToAllTasks = new Intent(AddTask.this, MainActivity.class);
                AddTask.this.startActivity(addTaskToAllTasks);

            }
        });
    }
}
