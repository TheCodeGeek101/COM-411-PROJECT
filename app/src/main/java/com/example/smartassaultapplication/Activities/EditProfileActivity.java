package com.example.smartassaultapplication.Activities;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartassaultapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditProfileActivity extends AppCompatActivity {
    FirebaseFirestore db;
   EditText etFullname, etEmail, etOldPassword, etNewPassword;
  FirebaseUser user;
  Button btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        db = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        etFullname = findViewById(R.id.fullname);
        etEmail = findViewById(R.id.etRegisterEmail);
        etOldPassword = findViewById(R.id.et_old_Password);
        etNewPassword = findViewById(R.id.new_password);
        btnUpdate = findViewById(R.id.updateProfile);
        btnUpdate.setOnClickListener(view -> storeUserData());
    }

    private void storeUserData(){
        String fullname =  etFullname.getText().toString();
        String oldpassword = etOldPassword.getText().toString();
        String newPassword = etNewPassword.getText().toString();
        String email = etEmail.getText().toString();
//        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        DatabaseReference usernameRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId).child("name");
//        usernameRef.setValue(fullname);
        if(oldpassword != newPassword){
            user.updatePassword(newPassword)
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(this,MainActivity.class);
                            startActivity(intent);
                            finish();
                            System.out.println("Password changed successfully");
                            //                        Toast.makeText(this,"Password changed successfully",Toast.LENGTH_LONG);
                        }
                        else{

                        }
                    }).addOnFailureListener(e -> Log.d(TAG,e.getMessage()));
        }
        else{
            Toast.makeText(this,"Please provide a different password",Toast.LENGTH_LONG);
        }
        user.updateEmail(email).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                System.out.println("Email changed successfully");
            }
        }).addOnFailureListener(e -> Log.d(TAG,e.getMessage()));

        // Create a new user with a first and last name
//        Map<String, Object> userData = new HashMap<>();
//        userData.put("first", "Ada");
//        userData.put("last", "Lovelace");
//        userData.put("born", 1815);
//
//// Add a new document with a generated ID
//        db.collection("users")
//                .add(userData)
//                .addOnSuccessListener(documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId()))
//                .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));
    }
}