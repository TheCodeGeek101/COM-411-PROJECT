package com.example.smartassaultapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {


    boolean passwordVisible;
    private FirebaseAuth smartAssaultAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        smartAssaultAuth = FirebaseAuth.getInstance();

        EditText etLoginEmail;
        EditText etLoginPassword;

        etLoginEmail = findViewById(R.id.etLoginEmail);
        etLoginPassword = findViewById(R.id.etLoginPassword);

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(view -> authenticateSoldier());

        TextView tvSwitchToRegister = findViewById(R.id.tvSwitchtoRegister);
        tvSwitchToRegister.setOnClickListener(view -> switchToRegister());

        etLoginPassword.setOnTouchListener((view, motionEvent) -> {
                final int Right = 2;
                if(motionEvent.getAction() == MotionEvent.ACTION_UP)
                {
                    if(motionEvent.getRawX() >= etLoginPassword.getRight()-etLoginPassword.getCompoundDrawables()[Right].getBounds().width())
                    {
                        int selection = etLoginPassword.getSelectionEnd();
                        if(passwordVisible){
//                                setting the password visibility off icon here
                            etLoginPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility,0);
//                                hiding the password
                            etLoginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible = false;
                        }else {
//                                setting the password visibility on icon here
                            etLoginPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibilityon,0);
//                                showing the password
                            etLoginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible = true;
                        }
                        etLoginPassword.setSelection(selection);

                        return  true;
                    }
                }
            return false;
        });

    }

    private void switchToRegister() {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    private void authenticateSoldier() {
        EditText editTextEmail = findViewById(R.id.etLoginEmail);
        EditText editTextPassword = findViewById(R.id.etLoginPassword);

        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        smartAssaultAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        redirectToHome();
                    } else {
                        Toast.makeText(LoginActivity.this,"Authentication Failed",Toast.LENGTH_LONG).show();
                    }
                });

    }

    private void redirectToHome() {
        Intent intent  = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = smartAssaultAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }


}