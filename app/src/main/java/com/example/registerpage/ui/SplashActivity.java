package com.example.registerpage.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.registerpage.LoginActivity;
import com.example.registerpage.MainActivity;
import com.example.registerpage.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAuth=FirebaseAuth.getInstance();
        mUser= mAuth.getCurrentUser();

        checkIfLogin();
        findViewById(R.id.tv_login).setOnClickListener(view -> {
            finish();
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        });
    }

    private void checkIfLogin() {
        if (mUser != null){
            sendUserToNextActivity();
        }
    }

    private void sendUserToNextActivity() {
        Intent intent= new Intent(SplashActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}