package com.pvobrien.github.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ((Button) findViewById(R.id.signUpButton)).setOnClickListener(view -> {
            EditText userName = findViewById(R.id.usernameCreate);
            EditText userEmail = findViewById(R.id.userSigninEmail);
            EditText userPasswordCreate = findViewById(R.id.passwordCreate);

            Amplify.Auth.signUp(
                    userName.getText().toString(),
                    userPasswordCreate.getText().toString(),
                    AuthSignUpOptions.builder().userAttribute(AuthUserAttributeKey.email(), userEmail.getText().toString()).build(),
                    result -> Log.i("Amplify.signUp", "Results: " + result.toString()),
                    error -> Log.e("Amplify.signUp", "Sign up FAIL", error)
            );

            Intent intent = new Intent(this, SignUpConfirm.class);

            intent.putExtra("username", userName.getText().toString());
            intent.putExtra("password", userPasswordCreate.getText().toString());

            this.startActivity(intent);
        });
    }
}