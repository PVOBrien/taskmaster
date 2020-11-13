package com.pvobrien.github.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;

public class SignUpConfirm extends AppCompatActivity {

    Handler toastHandler;
    Message message = new Message();

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
                    result -> {
                        Log.i("Amplify.confirm", result.isSignUpComplete() ? "Confirm signup successful" : "Sign up confirm NOT successful");
//                        TODO: add handler here
                        message.arg1 = 123;
                        toastHandler.sendMessage(message);

                        Intent intent = getIntent();
                        String username = intent.getExtras().getString("username");
                        String password = intent.getExtras().getString("password");

                        Amplify.Auth.signIn(
                                username,
                                password,
                                newResult -> this.startActivity(new Intent(SignUpConfirm.this, MainActivity.class)),
                                error -> Log.e("Auth.Result", "failure")
                        );

                    },
                    error -> Log.e("Amplify.confirm", error.toString())
                );
            Intent intent = new Intent(SignUpConfirm.this, MainActivity.class);
            this.startActivity(intent);
        });

        toastHandler = new Handler(Looper.getMainLooper(), message -> {

            if (message.arg1 == 123) {
                Context context = getApplicationContext();
                CharSequence text = "User Confirmation Complete";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            return false; // always needs to be returning something (false is default, still needs to return it like so).
        });
    }
}

