package com.example.smartassaultapplication;

import static android.widget.Toast.LENGTH_LONG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    boolean passwordVisible;
    private FirebaseAuth smartAssaultAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText editTextPassword = findViewById(R.id.etRegisterPassword);

        smartAssaultAuth = FirebaseAuth.getInstance();

        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerSoldier();
            }
        });

        TextView textViewSwitchToLogin = findViewById(R.id.tvSwitchtoLogin);
        textViewSwitchToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToLogin();
            }
        });
        
        
        editTextPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int Right = 2;
                if(motionEvent.getAction() == MotionEvent.ACTION_UP)
                {
                    if(motionEvent.getRawX() >= editTextPassword.getRight()-editTextPassword.getCompoundDrawables()[Right].getBounds().width())
                    {
                        int selection = editTextPassword.getSelectionEnd();
                        if(passwordVisible)
                        {
//                                setting the password visibility off icon here
                            editTextPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility,0);
//                                hiding the password
                            editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible = false;
                        }
                        else {
//                                setting the password visibility on icon here
                            editTextPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibilityon,0);
//                                showing the password
                            editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible = true;
                        }
                        editTextPassword.setSelection(selection);

                        return  true;
                    }
                }
                return false;
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = smartAssaultAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }
    private void switchToLogin()
    {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void registerSoldier() {
        EditText editTextEmail = findViewById(R.id.etRegisterEmail);
        EditText editTextFullname = findViewById(R.id.fullname);
        EditText editTextPassword = findViewById(R.id.etRegisterPassword);

      String fullname = editTextFullname.getText().toString();
      String email = editTextEmail.getText().toString();
      String password = editTextPassword.getText().toString();

      if(fullname.isEmpty() || email.isEmpty() || password.isEmpty())
      {
          Toast.makeText(this,"Please fill in all fields", LENGTH_LONG).show();
          return;
      }

        smartAssaultAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Soldier soldier = new Soldier(fullname,email);
                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(soldier).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            redirectToHome();
                                        }
                                    });
                        }
                        else {
                            Toast.makeText(RegisterActivity.this,"Registration Error!", LENGTH_LONG).show();
                        }
                    }
                });

    }

    private void redirectToHome() {
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
        finish();

    }
}