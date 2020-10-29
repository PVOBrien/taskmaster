package com.pvobrien.github.taskmaster;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;

import java.util.ArrayList;

public class AddTask extends AppCompatActivity {

//    YourUniqueDatabase yourUniqueDatabase; // this is looking specifically for YOUR yourUniqueDatabase class name/potato

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        yourUniqueDatabase = Room.databaseBuilder(getApplicationContext(), YourUniqueDatabase.class, "taskDatabase") // this YourUniqueDatabase.class is looking for YOUR yourUniqueDatabase class name/potato.
//                    .allowMainThreadQueries()
//                    .build();

        ArrayList<Team> teams = new ArrayList<Team>();

        RadioButton radioButtonOne = this.findViewById(R.id.RadButOne);
        RadioButton radioButtonTwo = this.findViewById(R.id.RadButTwo);
        RadioButton radioButtonThree = this.findViewById(R.id.RadButThree);

        Amplify.API.query(
                ModelQuery.list(Team.class),
                response -> {
                    for (Team team: response.getData()) {
                        teams.add(team);
                    }
                    System.out.println("How many teams: " + teams.size());

                    radioButtonOne.setText(teams.get(0).getName());
                    radioButtonTwo.setText(teams.get(1).getName());
                    radioButtonThree.setText(teams.get(2).getName());

                    Log.i ("Amplify", "Teams is built.");
                },
                error -> Log.e ("Amplify", "failed to retrieve team")
        );

        TextView taskTitleTv = AddTask.this.findViewById(R.id.taskName);
        TextView taskDetailsTv  = AddTask.this.findViewById(R.id.taskDetails);
        TextView taskStatusTv = AddTask.this.findViewById(R.id.taskStatusTv);

        Context context = getApplicationContext();
        CharSequence text = "Task Entered";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);

//        Task taskToAdd = new Task(taskTitleTv.getText().toString(), taskDetailsTv.getText().toString(), taskStatusTv.getText().toString()); TODO: Reinstate

        Button addButton = AddTask.this.findViewById(R.id.addTaskButton);
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //  === find the Team ===

                RadioGroup teamRadGroup = AddTask.this.findViewById(R.id.teamRadG);
                RadioButton selectedTeam = AddTask.this.findViewById(teamRadGroup.getCheckedRadioButtonId());

                String teamName = selectedTeam.getText().toString();

                Team chosenTeam = null;

                for (int i = 0; i < teams.size(); i++) {
                    if(teams.get(i).getName().equals(teamName)) {
                        chosenTeam = teams.get(i);
                    }
                }

                TextView taskTitleTv = AddTask.this.findViewById(R.id.taskName);
                TextView taskDetailsTv  = AddTask.this.findViewById(R.id.taskDetails);
                TextView taskStatusTv = AddTask.this.findViewById(R.id.taskStatusTv);

//                Task taskToAdd = new Task(taskTitleTv.getText().toString(), taskDetailsTv.getText().toString(), taskStatusTv.getText().toString()); TODO: Reinstate

                // CREATE TASK via TaskLocal.builder()...

            Task newTask = Task.builder()
                    .taskDetails(taskDetailsTv.getText().toString())
                    .taskStateOfDoing(taskStatusTv.getText().toString())
                    .taskTitle(taskTitleTv.getText().toString())
                    .apartOf(chosenTeam)
                    .build();

            Amplify.API.mutate( // https://docs.amplify.aws/lib/graphqlapi/mutate-data/q/platform/android
                    ModelMutation.create(newTask),
                    response -> Log.i("Amplify", "success!"),
                    error -> Log.e("Amplify", "Failure", error)); // must be completely built and entirely correct in order to not be grumpy. Ie it's red from the get-go, and only after the ; is in place with all details in order does it quiet down.

//                yourUniqueDatabase.taskDao().saveTheThing(taskLocalToAdd);

                System.out.println(String.format("You have a new task called %s and you'll be %s all days long", taskTitleTv.getText().toString(), taskDetailsTv.getText().toString()));

                toast.show();

//                finish(); // this "closes out" the activity, sends us back to where we are.
//                onBackPressed(); // this basically does the same thing.
                finish();

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
