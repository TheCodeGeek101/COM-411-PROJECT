package com.example.smartassaultapplication.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartassaultapplication.R;

public class Settings extends AppCompatActivity {
    View inflatedView;

    private TextView tv_back;
    private View cl_sign_out, cl_privacy_policy, cl_terms_and_conditions,
            cl_contact_us, cl_notifications, cl_change_password, cl_edit_my_profile;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        findViews();
        initViews();
        initClickListeners();
    }

//    private void findViews() {
//        cl_sign_out = inflatedView.findViewById(R.id.cl_sign_out);
//        cl_privacy_policy = inflatedView.findViewById(R.id.cl_privacy_policy);
//        cl_terms_and_conditions = inflatedView.findViewById(R.id.cl_terms_and_conditions);
//        cl_contact_us = inflatedView.findViewById(R.id.cl_contact_us);
//        cl_notifications = inflatedView.findViewById(R.id.cl_notifications);
//        cl_change_password = inflatedView.findViewById(R.id.cl_change_password);
//        cl_edit_my_profile = inflatedView.findViewById(R.id.cl_edit_my_profile);
//    }

    private void initViews() {


    }

    private void initClickListeners() {

        tv_back.setOnClickListener(view -> {
            //add your here
        });
        cl_sign_out.setOnClickListener(view -> {
            //add your here
        });
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
            //add your here
        });

    }

}