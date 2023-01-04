package com.example.smartassaultapplication.Services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.List;

public class ActivityRecognitionService extends IntentService {
    /**
     * @param name
     * @deprecated
     */
    public ActivityRecognitionService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
        List<DetectedActivity> detectedActivities = result.getProbableActivities();

        for(DetectedActivity activity : detectedActivities){
            Log.e("ActivityRecognition","DetectedActivity:"+ activity);
            if(activity.getType() == DetectedActivity.IN_VEHICLE){
                Toast.makeText(this,"User is in a vehicle",Toast.LENGTH_LONG);
            }
            else if(activity.getType() == DetectedActivity.ON_FOOT){
                Toast.makeText(this,"User is on foot",Toast.LENGTH_LONG);
            }
            else if(activity.getType() == DetectedActivity.ON_BICYCLE){
                Toast.makeText(this,"User is on a bicycle",Toast.LENGTH_LONG);
            }
            else if(activity.getType() == DetectedActivity.ON_BICYCLE){
                Toast.makeText(this,"User is on a bicycle",Toast.LENGTH_LONG);
            }
            else if(activity.getType() == DetectedActivity.STILL){
                Toast.makeText(this,"User is on a steady position",Toast.LENGTH_LONG);
            }
        }
    }
}
