package com.example.registerpage.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.registerpage.R;
import com.example.registerpage.databinding.ActivityChangePasswordBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordActivity extends AppCompatActivity {

    private ActivityChangePasswordBinding binding;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth= FirebaseAuth.getInstance();
        mUser= mAuth.getCurrentUser();
        changePasswordClick();
    }

    private void changePasswordClick() {
        binding.btnlogin.setOnClickListener(view ->{
            String password = binding.edPassword.getText().toString().trim();
            String confirmPassword = binding.edConfirmPassword.getText().toString().trim();
            if (!password.equals(confirmPassword)){

                Toast.makeText(ChangePasswordActivity.this
                        , "Both passwords must be identical" , Toast.LENGTH_LONG).show();
            }else {
                changePassword(password );
            }
        });
    }

    private void changePassword(String password) {
        mUser.updatePassword(password).addOnSuccessListener(unused -> {
            Toast.makeText(ChangePasswordActivity.this
                    , "Password changed successfully" , Toast.LENGTH_LONG).show();
            finish();
        }).addOnFailureListener(unused ->{
            Toast.makeText(ChangePasswordActivity.this
                    , unused.toString() , Toast.LENGTH_LONG).show();

        });
    }
}