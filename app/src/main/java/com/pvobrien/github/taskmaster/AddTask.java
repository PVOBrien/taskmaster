package com.pvobrien.github.taskmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);


        Button addButton = AddTask.this.findViewById(R.id.addTaskButton);
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText taskName = AddTask.this.findViewById(R.id.taskName);
                EditText taskDetails = AddTask.this.findViewById(R.id.taskDetails);
                String newTaskName = taskName.getText().toString();
                String newTaskDetails = taskDetails.getText().toString();
                System.out.println(String.format("You have a new task called %s and you'll be %s all days long", newTaskName, newTaskDetails));

                Toast.makeText(getApplicationContext(), "Submitted!", Toast.LENGTH_LONG).show();

                Intent addTaskToAllTasks = new Intent(AddTask.this, AllTasks.class);
                addTaskToAllTasks.putExtra("name", newTaskName);
                addTaskToAllTasks.putExtra("details", newTaskDetails);
                AddTask.this.startActivity(addTaskToAllTasks);

            }
        });
    }
}
