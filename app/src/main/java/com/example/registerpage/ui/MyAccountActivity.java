package com.example.registerpage.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.registerpage.LoginActivity;
import com.example.registerpage.MainActivity;
import com.example.registerpage.R;
import com.example.registerpage.databinding.ActivityMyAccountBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MyAccountActivity extends AppCompatActivity {

    private ActivityMyAccountBinding binding;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        logoutClick();
    }

    private void logoutClick() {
        binding.btnSignOut.setOnClickListener(view ->{
            signOut();
        });
    }

    private void signOut() {

        mAuth.signOut();
        relaunchApp();
    }

    private void relaunchApp() {
        finishAffinity();
        Intent intent = new Intent(MyAccountActivity.this, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}