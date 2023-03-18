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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivtiy extends AppCompatActivity {


    TextView alreadyHaveAccount;
    EditText inputAcademicemail,inputpassword,inputConfirmPassword;
    Button btnRegister;
    String AcademicemailPattern ="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView btn =findViewById(R.id.alreadyHaveAccount);
        inputAcademicemail =findViewById(R.id.inputAcademicemail);
        inputpassword =findViewById(R.id.inputpassword);
        inputConfirmPassword =findViewById(R.id.inputConfirmPassword);
        btnRegister =findViewById(R.id.btnRegister);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser= mAuth.getCurrentUser();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivtiy.this,LoginActivity.class));
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perforAuth();
            }
        });


        }

    private void perforAuth() {
        String Academicemail = inputAcademicemail.getText().toString();
        String password = inputpassword.getText().toString();
        String ConfirmPassword = inputConfirmPassword.getText().toString();



        if (Academicemail.matches(AcademicemailPattern))
        {
            inputAcademicemail.setError("Enter Connext Academicemail");
        }
        else if (password.isEmpty() || password.length()<6){
            inputpassword.setError("Enter Proper Password");

        } else if (password.equals(ConfirmPassword)) {

            inputConfirmPassword.setError("Password Not match Both field");

        } else   {


            progressDialog.setMessage("Please Wait While Regisration.....");
            progressDialog.setTitle("Registraion");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(Academicemail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){

                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(RegisterActivtiy.this, "Registration Sucesssful", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivtiy.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    private void sendUserToNextActivity() {
        Intent intent= new Intent(RegisterActivtiy.this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
