package com.example.smartassaultapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    boolean passwordVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                    final int Right = 2;
                    if(motionEvent.getAction() == MotionEvent.ACTION_UP)
                    {
                        if(motionEvent.getRawX() >= password.getRight()-password.getCompoundDrawables()[Right].getBounds().width())
                        {
                            int selection = password.getSelectionEnd();
                            if(passwordVisible){
//                                setting the password visibility off icon here
                                password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility,0);
//                                hiding the password
                                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                passwordVisible = false;
                            }else {
//                                setting the password visibility on icon here
                                password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibilityon,0);
//                                showing the password
                                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                passwordVisible = true;
                            }
                            password.setSelection(selection);

                            return  true;
                        }
                    }
                return false;
            }
        });

    }
}