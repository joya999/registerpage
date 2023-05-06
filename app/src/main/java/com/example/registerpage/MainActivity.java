package com.example.registerpage;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.registerpage.adapters.LevelsAdapter;
import com.example.registerpage.databinding.ActivityMainBinding;
import com.example.registerpage.models.Level;
import com.example.registerpage.models.User;
import com.example.registerpage.ui.MyAccountActivity;
import com.example.registerpage.ui.SubjectsActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    LevelsAdapter levelsAdapter;
    private DatabaseReference mDatabase;
    private DatabaseReference levelsRef;
    private DatabaseReference userRef;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


        levelsAdapter = new LevelsAdapter(new ArrayList<>());
        binding.rvLevels.setAdapter(levelsAdapter);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        levelsRef = mDatabase.child("levels");
        Query query = levelsRef.orderByChild("id");
        query.addChildEventListener(childEventListener);
        toggleProgress(true);
        userRef = mDatabase.child("users").child(mUser.getUid());

        setUserData();
        adapterClick();
        settingsClick();
    }

    private void settingsClick() {
        binding.ivSettings.setOnClickListener(view ->{
            Intent intent = new Intent(MainActivity.this , MyAccountActivity.class);
            startActivity(intent);
        });
    }

    private void adapterClick() {
        levelsAdapter.setOnItemClickListener(this::launchSubjectsScreen);
    }

    private void launchSubjectsScreen(Level item) {
        Intent intent = new Intent(this , SubjectsActivity.class);
        intent.putExtra(Constants.LEVEL_ID , item.key);
        intent.putExtra(Constants.LEVEL_NAME_KEY , item.name);
        startActivity(intent);
    }

    private void setUserData() {
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                binding.tvName.setText(user.getName());
                binding.tvNum.setText(user.getAcademicNumber().toString());
                binding.tvEmail.setText(user.getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }


    ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
            Level level = dataSnapshot.getValue(Level.class);
            level.key = dataSnapshot.getKey();
            levelsAdapter.addItem(level);
            toggleProgress(false);
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
