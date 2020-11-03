package com.pvobrien.github.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;

public class SignUpConfirm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_confirm);

        ((Button) findViewById(R.id.codeConfirm)).setOnClickListener(view -> {
            EditText userEmailConfirm = findViewById(R.id.userEmailConfirm);
            EditText codeConfirm = findViewById(R.id.enterConfirmCode);

            Amplify.Auth.confirmSignUp(
                    userEmailConfirm.getText().toString(),
                    codeConfirm.getText().toString(),
                    result -> Log.i("Amplify.confirm", result.isSignUpComplete() ? "Confirm signup successul" : "Sign up confirm NOT successful"),
                    error -> Log.e("Amplify.confirm", error.toString())
                );
            Intent intent = new Intent(SignUpConfirm.this, Login.class);
            this.startActivity(intent);
        });
    }
}