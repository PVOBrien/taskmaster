package com.pvobrien.github.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Set;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor preferenceEditor = preferences.edit();

        RadioButton radioButtonOne = this.findViewById(R.id.Venusaur);
        RadioButton radioButtonTwo = this.findViewById(R.id.Blastoise);
        RadioButton radioButtonThree = this.findViewById(R.id.Charizard);

//        ArrayList<Team> teams = new ArrayList<Team>();
//
//        Amplify.API.query(
//                ModelQuery.list(Team.class),
//                response -> {
//                    for (Team team: response.getData()) {
//                        teams.add(team);
//                    }
//                    System.out.println("How many teams: " + teams.size());
//
//                    radioButtonOne.setText(teams.get(0).getName());
//                    radioButtonTwo.setText(teams.get(1).getName());
//                    radioButtonThree.setText(teams.get(2).getName());
//
//                    Log.i ("Amplify", "Teams is built.");
//                },
//                error -> Log.e ("Amplify", "failed to retrieve team")
//        );



        findViewById(R.id.saveSettingButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RadioGroup userTeamPref = Settings.this.findViewById(R.id.usersTeamChoices);
                RadioButton selectedTeamPref = Settings.this.findViewById(userTeamPref.getCheckedRadioButtonId());
                String selectedTeamPrefToString = selectedTeamPref.getText().toString();

                EditText username = findViewById(R.id.usernameText);
                System.out.println(selectedTeamPrefToString);
                preferenceEditor.putString("savedTeam", selectedTeamPref.getText().toString());
                preferenceEditor.putString("savedUsername", username.getText().toString());
                preferenceEditor.apply();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mtIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(mtIntent, 0);
        return true;
    }

}