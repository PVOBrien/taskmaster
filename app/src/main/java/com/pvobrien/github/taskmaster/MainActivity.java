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
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

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
    SharedPreferences preferences;
    Handler handleCheckedLogin;

    @Override
    public void onResume() {
        super.onResume();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        TextView myTaskTitle = findViewById(R.id.myTasksTitle);
//        String greeting = String.format("%s's tasks", preferences.getString("savedUsername", "User's"));
//        myTaskTitle.setText(greeting);

        System.out.println("About to Query database.");

        getIsSignedIn();

        Amplify.API.query(
                ModelQuery.list(Task.class),
                response -> {
                    tasks.clear(); // keeps the same array list, but empties it.
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
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler(Looper.getMainLooper(),

                new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message message) {
                        recyclerView.getAdapter().notifyDataSetChanged();
                        return true;
                    }
                });


//      setupTheTeams();

        configureAWS();

        tasks = new ArrayList<>(); // TODO necessary?

        System.out.println("This is in the Task API query");

        Amplify.API.query(
                ModelQuery.list(Team.class),
                response -> {
                    teams = new ArrayList<>();
                    for(Team team : response.getData()) {
                        teams.add(team);
                    }
                    System.out.println("here are teams" + teams);
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("Amplify", "failure to retrieve")
        );

        recyclerView = findViewById(R.id.tasksRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TaskAdapter(tasks, this));



// ===== AWS subscription detail =====================

        ApiOperation subscription = Amplify.API.subscribe(
                ModelSubscription.onCreate(Task.class),
                onEstablished -> Log.i("Sub works", "Sub connected"),
                taskDiscovered -> {
                    Log.i("ApiQuickStart", "Here's the ticking work: " + ((Task) taskDiscovered.getData()).getTaskTitle()
                    );
                    Log.i("ApiQuickStart", "Here's the photo *key*: " + ((Task) taskDiscovered.getData()).getFilekey()
                    );

                    Task newTask = taskDiscovered.getData();
                    if (preferences.contains("savedTeam")) {
                        System.out.println("There is a team: " + preferences.getString("savedTeam", "NA"));
                        if (newTask.getApartOf().getName() == preferences.getString("savedTeam", "NA")) {
//                          TODO: Add team preference logic.
                            System.out.println(newTask.apartOf.getName());
                            tasks.add(newTask);
                            handlerOfThisSingleItemAdded.sendEmptyMessage(1);
                        }
                    }
                },
                onFailure -> Log.e("ApiQuickStart", "sub fail", onFailure),
                () -> Log.i("ApiQuickStart", "Sub fulfilled")
        );

        handlerOfThisSingleItemAdded = new Handler(Looper.getMainLooper(),
                (message -> {
                    recyclerView.getAdapter().notifyItemInserted(tasks.size() - 1);
                    // TODO: make toast here.
                    Toast.makeText(
                            this,
                            tasks.get(tasks.size() - 1).taskTitle + " is now a task added.",
                            Toast.LENGTH_SHORT).show();
                    return false;
                }));

        handleCheckedLogin = new Handler(Looper.getMainLooper(), message -> { // TODO create this
            if (message.arg1 == 0){
                Log.i("Amplify.login", "handler: UNlogged user.");
            } else if(message.arg1 == 1) {
                Log.i("Amplify.login", "handler: Logged IN");
                Log.i("Amplify.user is: ", Amplify.Auth.getCurrentUser().getUsername());

                TextView myTaskTitle = findViewById(R.id.myTasksTitle);
                String greeting = String.format("%s's tasks", Amplify.Auth.getCurrentUser().getUsername());
                myTaskTitle.setText(greeting);

            } else {
                Log.i("Amplify:login", "T/F plz");
            }

            // TODO add in on screen TEXT.
            return false;
        });

// ====  buttons below here  ====================================================

        Button goToAddTask = MainActivity.this.findViewById(R.id.addTask);
        goToAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("head to the taskLocals...");
                Intent goToAddTasks = new Intent(MainActivity.this, AddTask.class);
                MainActivity.this.startActivity(goToAddTasks);
            }
        });

        ImageButton goToSettings = MainActivity.this.findViewById(R.id.homeToSettingsButton);
        goToSettings.setOnClickListener((view) -> {
            System.out.println("heading to settings.");
            Intent goToSettingPage = new Intent(MainActivity.this, Settings.class);
            MainActivity.this.startActivity(goToSettingPage);
        });

        ((Button) findViewById(R.id.signUpMain)).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Signup.class);
            MainActivity.this.startActivity(intent);
        });

        ((Button) findViewById(R.id.loginMain)).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Login.class);
            MainActivity.this.startActivity(intent);
        });

        ((Button) findViewById(R.id.signOutMain)).setOnClickListener(view -> {
            Amplify.Auth.signOut(
                    () -> Log.i("Amplify.Signout", "sign out successful"),
                    error -> Log.e ("Amplify.Signout", error.toString())
            );
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }


    // ==================== buttons above here =========

    public void configureAWS() {

        try {
            Amplify.addPlugin(new AWSApiPlugin());              // this is provided by implementation 'com.amplifyframework:aws-api:1.4.1'
            Amplify.addPlugin(new AWSCognitoAuthPlugin());      // If this gives you grief, wipe the emulator via the AVD Manager and try again. https://stackoverflow.com/questions/42816127/waiting-for-target-device-to-come-online
            Amplify.addPlugin(new AWSCognitoAuthPlugin());      // for S3 storage
            Amplify.addPlugin(new AWSS3StoragePlugin());        // for S3 storage
            Amplify.configure(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized Amplify");


        } catch (AmplifyException error) {
            String errorMsg = error.getLocalizedMessage();
            if (errorMsg.contentEquals("The client tried to add a plugin after calling configure().")) { // it's not just .equals(), it is .content(*cs*) to match a String
                Log.i("MyAmplifyApp", "Tried reinitializing Amplify."); //TODO find the actual string or use contains().
            } else {
                Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
            }
        }
    }

    @Override
    public void tasksToDoListener(Task task) {
        Intent intent = new Intent(MainActivity.this, TaskDetail.class);
        intent.putExtra("taskTitle", task.getTaskTitle());
        intent.putExtra("taskDetails", task.getTaskDetails());
        intent.putExtra("taskState", task.getTaskStateOfDoing());
        intent.putExtra("fileKey", task.getFilekey());
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

    public void getIsSignedIn() {
        Amplify.Auth.fetchAuthSession(
            result -> {
                Log.i("Amplify.Login", result.toString());
                Message message = new Message();

                if (result.isSignedIn()) {
                    message.arg1 = 1;
                } else {
                    message.arg1 = 0;
                }
                handleCheckedLogin.sendMessage(message);
            },
            error -> Log.e("Amplify.Login", error.toString())
        );
    }
}
