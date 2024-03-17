package com.example.firebaserealtimedatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RetriveActivity extends AppCompatActivity {

   // TextView ageTV;
    RecyclerView recyclerView;
    UserAdapter userAdapter;
    List<UserClass>userClassList;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrive);

        Initialize();

        //Retrive Data
       // RetriveSingleData();

        retriveData();
    }

    private void retriveData() {
        DatabaseReference dataRef=databaseReference.child("User Info");
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    userClassList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        UserClass userClass=dataSnapshot.getValue(UserClass.class);
                        userClassList.add(userClass);
                        userAdapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void RetriveSingleData() {

        DatabaseReference singleRef=databaseReference.child("User Info").child("-NelMx-L8NqDYzWeza2d").child("age");


       // add Listner or String Value Event Nile Update Korle App Close kore abr Open korte hobe
        //add ValueEvent Listner Nile Kicu Update Korle Insstant update hobe .
        singleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //snapshot AAy Data Store Thake
                if (snapshot.exists()){
                    String age=snapshot.getValue().toString();
                   // ageTV.setText(age);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void Initialize() {
       // ageTV=findViewById(R.id.ageTV);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userClassList=new ArrayList<>();
        userAdapter=new UserAdapter(userClassList);
        recyclerView.setAdapter(userAdapter);
        databaseReference= FirebaseDatabase.getInstance().getReference();
    }
}