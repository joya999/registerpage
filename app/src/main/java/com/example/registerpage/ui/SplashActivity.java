package com.example.registerpage.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.registerpage.LoginActivity;
import com.example.registerpage.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        findViewById(R.id.tv_login).setOnClickListener(view -> {
            finish();
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        });
    }
}