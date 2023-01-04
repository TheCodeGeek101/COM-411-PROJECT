package com.example.smartassaultapplication.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartassaultapplication.R;

public class Settings extends AppCompatActivity {
    private View  cl_privacy_policy, cl_terms_and_conditions,
            cl_contact_us, cl_notifications, cl_change_password, cl_edit_my_profile;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initViews();
        initClickListeners();
    }

    private void findViews() {
//        cl_sign_out = findViewById(R.id.cl_sign_out);
        cl_privacy_policy = findViewById(R.id.iv_privacy_policy_view);
        cl_terms_and_conditions = findViewById(R.id.iv_terms_and_conditions_view);
        cl_contact_us = findViewById(R.id.iv_contact_us_view);
        cl_notifications = findViewById(R.id.iv_notifications_view);
        cl_change_password = findViewById(R.id.iv_change_password_view);
        cl_edit_my_profile = findViewById(R.id.iv_edit_my_profile_view);
    }

    private void initViews() {


    }

    private void initClickListeners() {
        cl_privacy_policy.setOnClickListener(view -> {
            //add your here
        });
        cl_terms_and_conditions.setOnClickListener(view -> {
            //add your here
        });
        cl_contact_us.setOnClickListener(view -> {
            //add your here
        });
        cl_notifications.setOnClickListener(view -> {
            //add your here
        });
        cl_change_password.setOnClickListener(view -> {
            //add your here
        });

        cl_edit_my_profile.setOnClickListener(view -> {
            Intent intent = new Intent(this,EditProfileActivity.class);
            startActivity(intent);
        });

    }

}