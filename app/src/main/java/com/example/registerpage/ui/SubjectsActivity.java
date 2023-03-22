package com.example.registerpage.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.registerpage.Constants;
import com.example.registerpage.OnItemClickListener;
import com.example.registerpage.R;
import com.example.registerpage.adapters.SubjectsAdapter;
import com.example.registerpage.databinding.ActivityMainBinding;
import com.example.registerpage.databinding.ActivitySubjectsBinding;
import com.example.registerpage.models.Level;
import com.example.registerpage.models.Subject;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rajat.pdfviewer.PdfViewerActivity;

import java.util.ArrayList;

public class SubjectsActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private DatabaseReference subjectsRef;

    private String levelKey;
    private SubjectsAdapter subjectsAdapter;
    private ActivitySubjectsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubjectsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        subjectsAdapter = new SubjectsAdapter(new ArrayList<>());
        binding.rvSubjects.setAdapter(subjectsAdapter);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        levelKey = getIntent().getStringExtra("level_id");

        subjectsRef = mDatabase.child("levels").child(levelKey).child("subjects");

        subjectsRef.addChildEventListener(childEventListener);
        adapterClick();
    }

    private void adapterClick() {
        subjectsAdapter.setOnItemClickListener(item -> {
            Intent intent = new Intent(this, FilesGroupActivity.class);
            intent.putExtra(Constants.LEVEL_ID, levelKey);
            intent.putExtra(Constants.SUBJECT_ID, item.key);
            startActivity(intent);

        });
    }

    ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
            Subject subject = dataSnapshot.getValue(Subject.class);
            subject.key = dataSnapshot.getKey();
            subjectsAdapter.addItem(subject);

        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
    };

}