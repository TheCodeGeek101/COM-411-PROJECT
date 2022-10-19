package com.example.smartassaultapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

        private FirebaseAuth smartAssaultAuth;
        private DatabaseReference mDatabase;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
        TextView textEmail = findViewById(R.id.textEmail);
        TextView textFullname = findViewById(R.id.textFullname);

        smartAssaultAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = smartAssaultAuth.getCurrentUser();

        if(currentUser == null)
        {
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);

        }
        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutUser();
            }
        });

// ...
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//        DatabaseReference reference = firebaseDatabase.getReference("users").child(currentUser.getUid());
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Soldier soldier = snapshot.getValue(Soldier.class);
//                if(soldier != null)
//                {
//                    textFullname.setText("Fullname:" + soldier.fullname);
//                    textEmail.setText("Email Address:" + soldier.email);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//
//

    }
    private void logoutUser() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}