package com.example.registerpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.registerpage.databinding.ActivityRegisterBinding;
import com.example.registerpage.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivtiy extends BaseActivity {

    String AcademicemailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+\\.+[a-z]+";

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    private DatabaseReference mDatabase;

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perforAuth();
            }
        });


    }

    private void perforAuth() {
        String Academicemail = binding.inputAcademicemail.getText().toString();
        String password = binding.inputpassword.getText().toString();
        String name = binding.etName.getText().toString();
        String number = binding.etNumber.getText().toString();

        if (!Academicemail.matches(AcademicemailPattern)) {
            binding.inputAcademicemail.setError("Enter Connext Academicemail");
        } else if (password.isEmpty() || password.length() < 6) {
            binding.inputpassword.setError("Enter Proper Password");

        } else if (name.isEmpty() || name.length() < 3) {
            binding.etName.setError("Enter Proper Name");
        } else if (number.isEmpty() || number.length() < 6) {
            binding.etNumber.setError("Enter Proper Academic number");
        } else {
            toggleProgress(true);
            mAuth.createUserWithEmailAndPassword(Academicemail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {

                        mUser = mAuth.getCurrentUser();
                        String userId = mUser.getUid();
                        saveUserToDatabase(userId);
                    } else {
                        toggleProgress(false);
                        Toast.makeText(RegisterActivtiy.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    private void saveUserToDatabase(String userId) {
        String name = binding.etName.getText().toString();
        String email = binding.inputAcademicemail.getText().toString();
        String number = binding.etNumber.getText().toString();
        User user = new User(Long.valueOf(number), name, email);
        mDatabase.child(Constants.FIREBASE_USERS_KEY).child(userId).setValue(user).addOnCompleteListener(task -> {
            sendUserToNextActivity();
        }).addOnFailureListener(e -> {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        });
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(RegisterActivtiy.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finishAffinity();
    }
}
