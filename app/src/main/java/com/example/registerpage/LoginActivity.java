package com.example.registerpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    TextView createnewAccount;
    EditText inputAcademicemail,inputpassword,inputConfirmPassword;
    Button btnLogin;
    String AcademicemailPattern ="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
   

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createnewAccount=findViewById(R.id.createnewAccount);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);





        ImageView googlebtn = findViewById(R.id.btnGoogle);
        ImageView facebookbtn = findViewById(R.id.btnFacebook);
        TextView btn=findViewById(R.id.textViewSignUp);
        View btnlogin = findViewById(R.id.btnlogin);
        inputAcademicemail =findViewById(R.id.inputAcademicemail);
        inputpassword =findViewById(R.id.inputpassword);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser= mAuth.getCurrentUser();
        checkIfLogin();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivtiy.class));
            }
        });



        googlebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "google Login", Toast.LENGTH_SHORT).show();
            }
        });
        facebookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "facebook Login", Toast.LENGTH_SHORT).show();

            }
        });


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perforLogin();
            }
        });




    }

    private void checkIfLogin() {
        if (mUser != null){
            sendUserToNextActivity();
        }
    }

    private void perforLogin() {

        String Academicemail = inputAcademicemail.getText().toString();
        String password = inputpassword.getText().toString();

        if (!Academicemail.matches(AcademicemailPattern))
        {
            inputAcademicemail.setError("Enter Connext Academicemail");
        }
        else if (password.isEmpty() || password.length()<6){
            inputpassword.setError("Enter Proper Password");

        }
        else   {

            progressDialog.setMessage("Please Wait While Login....");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mAuth.signInWithEmailAndPassword(Academicemail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(LoginActivity.this, "Login Sucesssful", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }

                }
            });



        }

    }
    private void sendUserToNextActivity() {
        Intent intent= new Intent(LoginActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}