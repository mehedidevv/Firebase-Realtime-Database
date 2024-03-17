package com.example.firebaserealtimedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText nameET,phoneET,ageET;
    Button storeuserBTN;

    String name,phone,age;

    //Database Refferemce Create korte hbe
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Connection All ID
        connection();
        storeuserBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=nameET.getText().toString();
                phone=phoneET.getText().toString();
                age=ageET.getText().toString();

                if (name.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter Your Name", Toast.LENGTH_SHORT).show();
                } else if (phone.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter Your Phone Number", Toast.LENGTH_SHORT).show();
                } else if (age.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter Your Age", Toast.LENGTH_SHORT).show();
                }else {

                    SaveUserDataIntoDatabase();
                }
            }
        });
    }

    private void SaveUserDataIntoDatabase() {
        //Child Add Korte Hobe
        DatabaseReference userdata=databaseReference.child("User Info");
        //new User Er Jonno Alada Alada Child create Hobe
        String uID=databaseReference.push().getKey();

        UserClass userClass=new UserClass(name,phone,age);
        userdata.child(uID).setValue(userClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "User Data Added...", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(MainActivity.this, "Error:"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void connection() {
        nameET=findViewById(R.id.nameET);
        phoneET=findViewById(R.id.phoneET);
        ageET=findViewById(R.id.ageET);
        storeuserBTN=findViewById(R.id.storeuserBTN);

        //Firebase Instance Create korte hbe
        databaseReference= FirebaseDatabase.getInstance().getReference();
    }

    public void retrivedata(View view) {
        startActivity(new Intent(MainActivity.this,RetriveActivity.class));
    }
}