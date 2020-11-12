package com.pvobrien.github.taskmaster;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.room.Room;

import com.amazonaws.util.IOUtils;
import com.amplifyframework.analytics.AnalyticsEvent;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Coordinates;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddTask extends AppCompatActivity {

//    YourUniqueDatabase yourUniqueDatabase; // this is looking specifically for YOUR yourUniqueDatabase class name/potato
    ArrayList<Team> teams = new ArrayList<Team>();
    Handler handleCreation;
    String lastFileIUploaded;
    Uri imageFromIntent;
    FusedLocationProviderClient fusedLocationProviderClient;
    Location currentLocation;
    String addressString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        yourUniqueDatabase = Room.databaseBuilder(getApplicationContext(), YourUniqueDatabase.class, "taskDatabase") // this YourUniqueDatabase.class is looking for YOUR yourUniqueDatabase class name/potato.
//                    .allowMainThreadQueries()
//                    .build();

        askForPermissionToUseLocation();
        configureLocationServices();
        locationRequest();

        Intent intent = getIntent();
        if (intent.getType() != null) { // https://www.programcreek.com/java-api-examples/?class=android.content.Intent&method=getClipData
            Log.i("Amplify.addPic", "This is the full intent came across: " + intent.toString());
//            if (intent.getType().equals("image/*")) { TODO: why doesn't this work?
                Log.i("Amplify.addPic", "Within 'image/' : " + intent.getClipData().getItemAt(0));
                imageFromIntent = intent.getClipData().getItemAt(0).getUri();
                Log.i("Amplify.uri", "URI : " + imageFromIntent);
                ImageView image = findViewById(R.id.uploadedPic);
                image.setImageURI(imageFromIntent);
            }


        handleCreation = new Handler(Looper.getMainLooper(), message -> {
           setupTeamSpinner();
           return false;
        });

        Amplify.API.query(
                ModelQuery.list(Team.class),
                response -> {
                    for (Team team: response.getData()) {
                        teams.add(team);
                    }
                    System.out.println("How many teams: " + teams.size());
                    handleCreation.sendEmptyMessage(1);
                    Log.i ("Amplify", "Teams is built.");
                },
                error -> Log.e ("Amplify", "failed to retrieve team")
        );

        setupStatusSpinner();

        TextView taskTitleTv = AddTask.this.findViewById(R.id.taskName);
        TextView taskDetailsTv  = AddTask.this.findViewById(R.id.taskDetails);
//        TextView taskStatusTv = AddTask.this.findViewById(R.id.taskStatusTv);

//        Task taskToAdd = new Task(taskTitleTv.getText().toString(), taskDetailsTv.getText().toString(), taskStatusTv.getText().toString()); TODO: Reinstate

        addListenerToAddPicButton();

        Button addButton = AddTask.this.findViewById(R.id.addTaskButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  === find the Team ===
                Spinner teamNameSpinner = findViewById(R.id.teamSpinner);
                String teamName = teamNameSpinner.getSelectedItem().toString();
                Team chosenTeam = null;

                for (int i = 0; i < teams.size(); i++) {
                    if(teams.get(i).getName().equals(teamName)) {
                        chosenTeam = teams.get(i);
                    }
                }

                AnalyticsEvent taskCreated = AnalyticsEvent.builder() // the basic pinpoint event builder. build'em as you need them,
                        .name("TaskCreate")
                        .addProperty("time", Long.toString(new Date().getTime())) // using java.util for Date(), not sql
                        .addProperty("ItemIn", "going places")
                        .build();
                Amplify.Analytics.recordEvent(taskCreated);

                Spinner statusSpinner = findViewById(R.id.statusSpinner);
                String statusSelected = statusSpinner.getSelectedItem().toString();

                TextView taskTitleTv = AddTask.this.findViewById(R.id.taskName);
                TextView taskDetailsTv = AddTask.this.findViewById(R.id.taskDetails);

//                TextView taskStatusTv = AddTask.this.findViewById(R.id.taskStatusTv);
//                Task taskToAdd = new Task(taskTitleTv.getText().toString(), taskDetailsTv.getText().toString(), taskStatusTv.getText().toString()); TODO: Reinstate
                // CREATE TASK via TaskLocal.builder()...


                if (lastFileIUploaded == null) {
                    File fileCopy = new File(getFilesDir(), "fromOutsideIntent");
                    try {
                        InputStream inStream = getContentResolver().openInputStream(imageFromIntent);
                        FileOutputStream out = new FileOutputStream(fileCopy);
                        copyStream(inStream, out);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("Amplify.ImageUpload", e.toString());
                    }
                    uploadFile(fileCopy, fileCopy.getName() + Math.random());
                }

                Coordinates newCoordinates = Coordinates.builder()
                        .latitude(25.464492f) // Todo: Find the real coordinates, not hardcoded lol.
                        .longitude(-24.396790f)
                        .build();

                Amplify.API.mutate(
                        ModelMutation.create(newCoordinates),
                        response -> Log.i("Amplify.Coordinates", "success!" + newCoordinates.toString()),
                        error -> Log.e("Amplify.Coordinates", "no dice." + error)
                );

            Task newTask = Task.builder()
                    .taskDetails(taskDetailsTv.getText().toString())
                    .taskStateOfDoing(statusSelected)
                    .taskTitle(taskTitleTv.getText().toString())
                    .apartOf(chosenTeam)
                    .filekey(lastFileIUploaded)
                    .address(addressString)
                    .latlon(newCoordinates)
                    .build();


            Amplify.API.mutate( // https://docs.amplify.aws/lib/graphqlapi/mutate-data/q/platform/android
                    ModelMutation.create(newTask),
                    response -> Log.i("Amplify", "success!"),
                    error -> Log.e("Amplify", "Failure", error)); // must be completely built and entirely correct in order to not be grumpy. Ie it's red from the get-go, and only after the ; is in place with all details in order does it quiet down.

//                yourUniqueDatabase.taskDao().saveTheThing(taskLocalToAdd);

                System.out.println(String.format("You have a new task called %s and you'll be %s all days long", taskTitleTv.getText().toString(), taskDetailsTv.getText().toString()));

                Context context = getApplicationContext();
                CharSequence text = "Task Entered";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

//              onBackPressed(); // this basically does the same thing.
                finish(); // this "closes out" the activity, sends us back to where we are.

//              Intent addTaskToAllTasks = new Intent(AddTask.this, MainActivity.class);
//              AddTask.this.startActivity(addTaskToAllTasks);
            }
        });
    }

    public void addListenerToAddPicButton(){
        Button addPicture = findViewById(R.id.addPicture_addTaskView);
        addPicture.setOnClickListener((view -> retrieveFile()));
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2020) { // the number is POTATO

            Log.i("Amplify.pickImage", "Image has been retrieved."); // This will know, well enough

            File fileCopy = new File(getFilesDir(), "fileUpload"); // Todo: what is this child? It is the filename that is "appended to the front of the file.

            try {
                InputStream inStream = getContentResolver().openInputStream(data.getData()); // https://stackoverflow.com/questions/11501418/is-it-possible-to-create-a-file-object-from-inputstream
                FileOutputStream out = new FileOutputStream(fileCopy);
                copyStream(inStream, out);

//                FileUtils.copy(outfile, new FileOutputStream(fileCopy)); // COMPLETED: find the work around.

            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Amplify.pickImage", e.toString());
            }

            uploadFile(fileCopy, fileCopy.getName() + Math.random()); // TODO: refactor the if/else statement out.
        } else if (requestCode == 2) {
            Log.i("Amplify.neverHere", "Go! Be Free!");
        } else {
            Log.i("Amplify.pickImage", "where you coming from?");
        }
    }

    public void uploadFile(File f, String key) {
        lastFileIUploaded = key;
        Amplify.Storage.uploadFile(
                key,
                f,
                result -> {
                    Log.i("Amplify.s3", "Successfully uploaded: " + result.getKey());
                    downloadFile(key);
                },
                storageFailure -> Log.e("Amplify.s3", "Upload:Failure.", storageFailure)
        );
    }

    public void downloadFile(String fileKey){
        Amplify.Storage.downloadFile(
                fileKey,
                new File(getApplicationContext().getFilesDir() + "/" + fileKey + ".txt"),
                result -> {
                    Log.i("Amplify.s3down", "Successfully downloaded: " + result.getFile().getName());
                    ImageView image = findViewById(R.id.uploadedPic);
                    image.setImageBitmap(BitmapFactory.decodeFile(result.getFile().getPath()));
                },
                error -> Log.e("Amplify.s3down", "Download:Failed.", error)
        );
    }

    public void retrieveFile(){
        Intent getPicIntent = new Intent((Intent.ACTION_GET_CONTENT));
        getPicIntent.setType("*/*");
        startActivityForResult(getPicIntent, 2020);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mtIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(mtIntent, 0);
        return true;
    }

    public void setupTeamSpinner(){
        String[] teamNames = new String[teams.size()];
        for (int i = 0; i < teams.size(); i++) {
            teamNames[i] = teams.get(i).getName();
        } // put the teams' names into an arraylist.

        Spinner spinner = (Spinner) findViewById(R.id.teamSpinner); // create a spinner object to put following things to...
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, teamNames);  // Specify layout - AKA simple_spinner_item to use when choice list come in...
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // adapt the adapter for a spinner.
        spinner.setAdapter(adapter); // set the spinner to the adapter for a spinner.
    }

    public void setupStatusSpinner() {
        String[] stati = {"new","assigned","in progress", "complete"};
        Spinner spinner = (Spinner) findViewById(R.id.statusSpinner);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stati);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }


    public static void copyStream(InputStream in, OutputStream out) throws IOException { // https://stackoverflow.com/questions/9292954/how-to-make-a-copy-of-a-file-in-android
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    public void askForPermissionToUseLocation() {
        String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
        requestPermissions(permissions, 2); // Todo: add second param. What is the second param for?
    }

    public void configureLocationServices(){
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    }

    public void locationRequest() { // follow along from https://github.com/mdomeck/taskmaster/blob/labe-42/app/src/main/java/com/mdomeck/taskmaster/AddTask.java Thanks to mdomeck.

        LocationRequest locationRequest;
        LocationCallback locationCallback;

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(60000);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
            if (locationResult == null) {
                return;
            }
            currentLocation = locationResult.getLastLocation();
            Log.i("Amplify.location", "This is the currentLocation: " + currentLocation.toString());

            Geocoder geocoder = new Geocoder(AddTask.this, Locale.getDefault());
            try {
                List<Address> addresses = geocoder.getFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude(), 10);
                addressString = addresses.get(0).getAddressLine(0);
                Log.i("Amplify.location", "Here's the address: " + addressString);
            } catch (IOException e) {
//              e.printStackTrace();
            }
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast t = new Toast(this);
            t.setText("Please check your permissions");
            t.setDuration(Toast.LENGTH_LONG);
            t.show();
            return;
        }

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, getMainLooper());
    }

}

// public void askForLocation(){
// locationProviderClient.getLastLocation()
// .addonSuccessListener(location -> Log.i()
// .addonFailureListener(error -> Log.e()
// .addOnCanceledListener(() -> Log.e())
// .addOnCompleteListener(complete -> Log.i
// }