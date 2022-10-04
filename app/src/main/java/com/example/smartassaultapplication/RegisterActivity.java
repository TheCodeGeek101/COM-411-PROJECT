package com.example.smartassaultapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class RegisterActivity extends AppCompatActivity {

   EditText editTextEmail = findViewById(R.id.email);
   EditText editTextPassword = findViewById(R.id.password);
   EditText editTextFullname = findViewById(R.id.fullname);
   EditText editTextConfirmPassword = findViewById(R.id.confirmPassword);

    boolean passwordVisible;
    private FirebaseAuth smartAssaultAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        smartAssaultAuth = FirebaseAuth.getInstance();
        if (smartAssaultAuth.getCurrentUser() == null)
        {
            finish();
            return;

        }
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
//                switchToLogin();
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

    private void registerSoldier() {
      String fullname = editTextFullname.getText().toString();
      String email = editTextEmail.getText().toString();
      String password = editTextPassword.getText().toString();
      String confirmPassword = editTextConfirmPassword.getText().toString();

      if(fullname.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty())
      {
          Toast.makeText(this,"Please fill in all fields", Toast.LENGTH_LONG).show();
          return;
      }

        smartAssaultAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                        } else {

                        }
                    }
                });

    }
}