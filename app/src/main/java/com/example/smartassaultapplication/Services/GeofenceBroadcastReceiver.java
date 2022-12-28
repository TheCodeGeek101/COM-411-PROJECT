package com.example.smartassaultapplication.Services;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import com.example.smartassaultapplication.Helpers.NotificationHelper;
import com.example.smartassaultapplication.Activities.MapsActivity;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;


public class GeofenceBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        Toast.makeText(context, "Geofence Triggered...", Toast.LENGTH_SHORT).show();
        NotificationHelper notificationHelper = new NotificationHelper(context);

        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if(geofencingEvent.hasError()){
            Log.d(TAG,"onReceive: Error Receiving geofence event");
            return;
        }
        List<Geofence> geofenceList = geofencingEvent.getTriggeringGeofences();
        Location location = geofencingEvent.getTriggeringLocation();

        for(Geofence geofence: geofenceList){
            Log.d(TAG,"onReceive:" + geofence.toString());
        }

        int transitionType = geofencingEvent.getGeofenceTransition();

        switch (transitionType){
            case Geofence.GEOFENCE_TRANSITION_ENTER:
                Toast.makeText(context, "Response team has arrived your vicinity!...", Toast.LENGTH_SHORT).show();
                notificationHelper.sendHighPriorityNotification(" teammate has arrived the vicinity!...","", MapsActivity.class);
                break;

            case Geofence.GEOFENCE_TRANSITION_DWELL:
                Toast.makeText(context, "teammate is within your vicinity!...", Toast.LENGTH_SHORT).show();
                notificationHelper.sendHighPriorityNotification("Response is within the vicinity!...","",MapsActivity.class);
                break;

            case Geofence.GEOFENCE_TRANSITION_EXIT:
                Toast.makeText(context, " teammate has left your vicinity!...", Toast.LENGTH_SHORT).show();
                notificationHelper.sendHighPriorityNotification("Response team has left the vicinity!...","",MapsActivity.class);
                break;
        }
    }
}