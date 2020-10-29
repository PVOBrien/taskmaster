package com.pvobrien.github.taskmaster;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.ApiOperation;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.api.graphql.model.ModelSubscription;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnInteractWithTasksToDoListener {

    //    YourUniqueDatabase yourUniqueDatabase;
    NotificationChannel channel;
    NotificationManager notificationManager;
    ArrayList<Task> tasks;
    ArrayList<Team> teams;
    RecyclerView recyclerView;
    Handler handler;
    Handler handlerOfThisSingleItemAdded;
    int teamWeAreOnIndex = 0;
    SharedPreferences preferences;

    @Override
    public void onResume() {
        super.onResume();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        TextView myTaskTitle = findViewById(R.id.myTasksTitle);
        String greeting = String.format("%s's tasks", preferences.getString("savedUsername", "User's"));
        myTaskTitle.setText(greeting);
//        SharedPreferences.Editor preferenceEditor = preferences.edit();

//        Team chosenTeam = null;
//
//        for (int i = 0; i < teams.size(); i++) {
//            if(teams.get(i).getName().equals(preferences.getString("savedTeam", "Charizard"))) {
//                chosenTeam = teams.get(i);
//            }
//        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler(Looper.getMainLooper(),

                new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message message) {
//                        connectAdapterToRecyclerView();
                        recyclerView.getAdapter().notifyDataSetChanged();
                        return true;
                    }
                });


        try {
            Amplify.addPlugin(new AWSApiPlugin());  // this is provided by implementation 'com.amplifyframework:aws-api:1.4.1'
            Amplify.configure(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized Amplify");

//            setupTheTeams();

        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }

        tasks = new ArrayList<Task>();

        System.out.println("This is in the Task API query");

        Amplify.API.query(
                ModelQuery.list(Team.class),
                response -> {
                    teams = new ArrayList<Team>();
                    for(Team team : response.getData()) {
                        teams.add(team);
                    }
                    System.out.println("here are teams" + teams);
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("Amplify", "failure to retrieve")
        );


        Amplify.API.query(
                ModelQuery.list(Task.class),
                response -> {
                    for (Task task : response.getData()) {
                        if (preferences.contains("savedTeam")) {
                            if (task.getApartOf().getName().equals(preferences.getString("savedTeam", "NA"))) {
                                tasks.add(task);
                            }
                        } else { tasks.add(task); }
                    }
                    handler.sendEmptyMessage(1);
                    Log.i("amplify.queryItems", "Got this many: " + tasks.size());
                },
                error -> Log.i("Amplify.queryItems", "Did not receive tasks")
        );

        recyclerView = findViewById(R.id.tasksRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TaskAdapter(tasks, this));

//        yourUniqueDatabase = Room.databaseBuilder(getApplicationContext(), YourUniqueDatabase.class, "taskDatabase")
//                .fallbackToDestructiveMigration()
//                .allowMainThreadQueries()
//                .build();

//        ArrayList<Task> tasks = (ArrayList<Task>) yourUniqueDatabase.taskDao().getAllTasks();

        Handler handlerOfThisSingleItemAdded = new Handler(Looper.getMainLooper(),
                (message -> {
                    recyclerView.getAdapter().notifyItemInserted(tasks.size() - 1);
                    // TODO: make toast here.
                    Toast.makeText(
                            this,
                            tasks.get(tasks.size() - 1).taskTitle + " is now a task added.",
                            Toast.LENGTH_SHORT).show();
                    return false;
                }));

// ===== AWS subscription detail =====================

//        ApiOperation subscription = Amplify.API.subscribe(
//                ModelSubscription.onCreate(Task.class),
//                onEstablished -> Log.i("Sub works", "Sub connected"),
//                taskDiscovered -> {
//                    Log.i("ApiQuickStart", "Here's the ticking work: " + ((Task) taskDiscovered.getData()).getTaskTitle()
//                    );
//                    Task newTask = (Task) taskDiscovered.getData();
//                    tasks.add(newTask);
//                    handlerOfThisSingleItemAdded.sendEmptyMessage(1);
//                },
//                onFailure -> Log.e("ApiQuickStart", "sub fail", onFailure),
//                () -> Log.i("ApiQuickStart", "Sub fulfilled")
//        );


        Button goToAddTask = MainActivity.this.findViewById(R.id.addTask);
        goToAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("head to the taskLocals...");
                Intent goToAddTasks = new Intent(MainActivity.this, AddTask.class);
                MainActivity.this.startActivity(goToAddTasks);
            }
        });

        Button goToAllTasks = MainActivity.this.findViewById(R.id.allTasks);
        goToAllTasks.setOnClickListener((view) -> {
            System.out.println("Seeing all the taskLocals now.");
            Intent goToAllTasksNow = new Intent(MainActivity.this, RecyclerViewGeneric.class);
            MainActivity.this.startActivity(goToAllTasksNow);
        });

        ImageButton goToSettings = MainActivity.this.findViewById(R.id.homeToSettingsButton);
        goToSettings.setOnClickListener((view) -> {
            System.out.println("heading to settings.");
            Intent goToSettingPage = new Intent(MainActivity.this, Settings.class);
            MainActivity.this.startActivity(goToSettingPage);
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor preferenceEditor = preferences.edit();
    }

    @Override
    public void tasksToDoListener(Task task) {
        Intent intent = new Intent(MainActivity.this, TaskDetail.class);
        intent.putExtra("taskTitle", task.getTaskTitle());
        intent.putExtra("taskDetails", task.getTaskDetails());
        intent.putExtra("taskState", task.getTaskStateOfDoing());
        this.startActivity(intent);
    }

    public void setupTheTeams() {
        Team teamOne = Team.builder()
                .name("Charizard")
                .build();

        Team teamTwo = Team.builder()
                .name("Blastoise")
                .build();

        Team teamThree = Team.builder()
                .name("Venusaur")
                .build();

        String AMPTAG = "Amplify";
        String STOREADD = "Store added";
        String STOREFAIL = "failed to add store";

        Amplify.API.mutate(ModelMutation.create(teamOne),
                response -> Log.i(AMPTAG, STOREADD),
                error -> Log.e(AMPTAG, STOREFAIL)
        );

        Amplify.API.mutate(ModelMutation.create(teamTwo),
                response -> Log.i(AMPTAG, STOREADD),
                error -> Log.e(AMPTAG, STOREFAIL)
        );

        Amplify.API.mutate(ModelMutation.create(teamThree),
                response -> Log.i(AMPTAG, STOREADD),
                error -> Log.e(AMPTAG, STOREADD)
        );
    }

}
