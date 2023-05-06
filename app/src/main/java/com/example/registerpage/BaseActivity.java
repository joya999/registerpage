package com.example.registerpage;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    ProgressDialog pd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pd=new ProgressDialog(this);
        pd.setTitle("Loading");
        pd.setCanceledOnTouchOutside(false);
    }

    public void toggleProgress(Boolean isLoading){
        if (isLoading)
            pd.show();
        else
            pd.dismiss();
    }
}
