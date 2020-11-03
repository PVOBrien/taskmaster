package com.pvobrien.github.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ((Button) findViewById(R.id.loginButton)).setOnClickListener(view -> {
            EditText email = findViewById(R.id.emailLogin);
            EditText password = findViewById(R.id.passwordInput);

            Amplify.Auth.signIn(
                    email.getText().toString(),
                    password.getText().toString(),
                    result -> {
                        Log.i("Amplify.Login", result.isSignInComplete() ? "Sign in Successful" : "Sign in NOT successful");
                        startActivity(new Intent(Login.this, MainActivity.class));
                    },
                    error -> Log.e("Amplify.Login", error.toString())
            );
        });
    }
}