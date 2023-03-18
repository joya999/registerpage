package com.example.registerpage;

import static android.os.Build.VERSION_CODES.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<RCModel> modelArrayList;
    RCAdapter rcAdapter;
    String[] title = new String[]{
            "Level Two (computer Scince)", "Level Three (computer Scince)",
            "Level Four (computer Scince)"
    };
    int[] image = new int[]{

            
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //noinspection UnresolvedClassReferenceRepair

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        modelArrayList = new ArrayList<>();
        rcAdapter = new RCAdapter(this, modelArrayList);
        recyclerView.setAdapter(rcAdapter);


        for (int i = 0; i < title.length; i++) {
            RCModel rcModel = new RCModel(title[i], image[i]);
            modelArrayList.add(rcModel);
        }

        rcAdapter.notifyDataSetChange();

    }
}
