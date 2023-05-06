package com.example.registerpage.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.registerpage.R;
import com.example.registerpage.databinding.ActivityChangePasswordBinding;
import com.example.registerpage.databinding.ActivityForgetPasswordBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgetPasswordActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    String AcademicemailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private ActivityForgetPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        binding.btnReset.setOnClickListener(view -> {
            resetPassword();
        });
    }

    private void resetPassword() {
        String email = binding.inputAcademicemail.getText().toString().trim();
        if (email.isEmpty()){
            Toast.makeText(ForgetPasswordActivity.this
                    , "Password must not be empty" , Toast.LENGTH_LONG).show();
        }
        else if (!email.matches(AcademicemailPattern)) {
            binding.inputAcademicemail.setError("Enter Connext Academicemail");
        } else {
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(ForgetPasswordActivity.this, "Check your email", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(ForgetPasswordActivity.this, "Invalid email", Toast.LENGTH_LONG).show();

                }
            }).addOnFailureListener(e -> {
                Toast.makeText(ForgetPasswordActivity.this, e.toString(), Toast.LENGTH_LONG).show();

            });
        }
    }
}