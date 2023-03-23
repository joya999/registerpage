package com.example.registerpage.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.registerpage.Constants;
import com.example.registerpage.OnItemClickListener;
import com.example.registerpage.R;
import com.example.registerpage.adapters.FilesGroupAdapter;
import com.example.registerpage.adapters.LevelsAdapter;
import com.example.registerpage.adapters.SubjectsAdapter;
import com.example.registerpage.databinding.ActivityFilesGroupBinding;
import com.example.registerpage.databinding.ActivityMainBinding;
import com.example.registerpage.models.FilesGroup;
import com.example.registerpage.models.Subject;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FilesGroupActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private DatabaseReference filesGroupRef;

    private String levelKey;
    private String subjectKey;
    private String subjectName;


    private FilesGroupAdapter filesGroupAdapter;

    private ActivityFilesGroupBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFilesGroupBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        levelKey = getIntent().getStringExtra(Constants.LEVEL_ID);
        subjectKey = getIntent().getStringExtra(Constants.SUBJECT_ID);
        subjectName = getIntent().getStringExtra(Constants.SUBJECT_NAME_KEY);

        binding.tvTitle.setText(subjectName);
        filesGroupAdapter = new FilesGroupAdapter(new ArrayList<>());
        binding.rvFiles.setAdapter(filesGroupAdapter);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        filesGroupRef = mDatabase.child(Constants.FIREBASE_LEVELS_KEY)
                .child(levelKey).child(Constants.FIREBASE_SUBJECTS_KEY)
                .child(subjectKey).child(Constants.FIREBASE_FILES_GROUP_KEY);

        filesGroupRef.addChildEventListener(childEventListener);

        adapterClick();
    }

    private void adapterClick() {
        filesGroupAdapter.setOnItemClickListener(item -> {
            Intent intent = new Intent(this, FilesActivity.class);
            intent.putExtra(Constants.LEVEL_ID, levelKey);
            intent.putExtra(Constants.SUBJECT_ID, subjectKey);
            intent.putExtra(Constants.FILES_GROUP_ID, item.key);
            intent.putExtra(Constants.FILES_GROUP_NAME_KEY, item.name);
            startActivity(intent);
        });
    }

    ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
            FilesGroup filesGroup = dataSnapshot.getValue(FilesGroup.class);
            filesGroup.key = dataSnapshot.getKey();

            filesGroupAdapter.addItem(filesGroup);

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