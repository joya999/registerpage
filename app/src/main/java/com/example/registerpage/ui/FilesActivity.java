package com.example.registerpage.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.registerpage.Constants;
import com.example.registerpage.R;
import com.example.registerpage.adapters.FilesAdapter;
import com.example.registerpage.adapters.FilesGroupAdapter;
import com.example.registerpage.databinding.ActivityFilesBinding;
import com.example.registerpage.databinding.ActivityFilesGroupBinding;
import com.example.registerpage.models.File;
import com.example.registerpage.models.FilesGroup;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rajat.pdfviewer.PdfViewerActivity;

import java.util.ArrayList;

public class FilesActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private DatabaseReference filesRef;

    private String levelKey;
    private String subjectKey;
    private String filesGrouptKey;
    private String filesGrouptName;

    private FilesAdapter filesAdapter;
    private ActivityFilesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFilesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getBundledData();
        setAdapter();

        binding.tvTitle.setText(filesGrouptName);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        filesRef = mDatabase.child(Constants.FIREBASE_LEVELS_KEY)
                .child(levelKey).child(Constants.FIREBASE_SUBJECTS_KEY)
                .child(subjectKey).child(Constants.FIREBASE_FILES_GROUP_KEY)
                .child(filesGrouptKey).child(Constants.FIREBASE_FILES_KEY);

        filesRef.addChildEventListener(childEventListener);
    }

    private void setAdapter() {
        filesAdapter = new FilesAdapter(new ArrayList<>());
        binding.rvFiles.setAdapter(filesAdapter);
        adapterClick();
    }

    private void adapterClick() {
        filesAdapter.setOnItemClickListener(file -> {
            openPdf(file.url , file.name);
        });
    }

    private void getBundledData() {
        levelKey = getIntent().getStringExtra(Constants.LEVEL_ID);
        subjectKey = getIntent().getStringExtra(Constants.SUBJECT_ID);
        filesGrouptKey = getIntent().getStringExtra(Constants.FILES_GROUP_ID);
        filesGrouptName = getIntent().getStringExtra(Constants.FILES_GROUP_NAME_KEY);
    }

    private void openPdf(String url , String name) {
        startActivity(
                PdfViewerActivity.Companion.launchPdfFromUrl(
                        this, url, name
                        , "",
                        true
                )
        );
    }

    ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
            File file = dataSnapshot.getValue(File.class);
            filesAdapter.addItem(file);

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