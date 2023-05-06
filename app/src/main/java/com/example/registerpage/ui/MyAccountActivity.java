package com.example.registerpage.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.registerpage.LoginActivity;
import com.example.registerpage.MainActivity;
import com.example.registerpage.R;
import com.example.registerpage.databinding.ActivityMyAccountBinding;
import com.example.registerpage.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyAccountActivity extends AppCompatActivity {

    private ActivityMyAccountBinding binding;
    FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private DatabaseReference userRef;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        binding = ActivityMyAccountBinding.inflate(getLayoutInflater());
        mDatabase = FirebaseDatabase.getInstance().getReference();
        userRef = mDatabase.child("users").child(mUser.getUid());
        setUserData();
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        logoutClick();
        binding.tvChangePassword.setOnClickListener(view -> {
            launchChangePassword();
        });
    }

    private void logoutClick() {
        binding.btnSignOut.setOnClickListener(view -> {
            signOut();
        });
    }

    private void signOut() {

        mAuth.signOut();
        relaunchApp();
    }

    private void setUserData() {
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                binding.tvName.setText(user.getName());
                binding.tvEmail.setText(user.getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void relaunchApp() {
        finishAffinity();
        Intent intent = new Intent(MyAccountActivity.this, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void launchChangePassword() {
        Intent intent = new Intent(MyAccountActivity.this, ChangePasswordActivity.class);
        startActivity(intent);
    }
}