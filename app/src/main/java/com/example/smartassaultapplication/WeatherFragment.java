package com.example.smartassaultapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;


public class WeatherFragment extends Fragment {

    public WeatherFragment() {
        // Required empty public constructor
    }

    Button btLocation;
    TextView tvLatitude, tvLongitude;
    FusedLocationProviderClient client;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater,
                             @NonNull ViewGroup container,
                             @NonNull  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather,
                container, false);

        // Assign variable
        btLocation = view.findViewById(R.id.bt_location);
        tvLatitude = view.findViewById(R.id.tv_latitude);
        tvLongitude = view.findViewById(R.id.tv_longitude);

        // Initialize location client
        client = LocationServices.getFusedLocationProviderClient(getActivity());
        // Inflate the layout for this fragment
        btLocation.setOnClickListener(
                new View.OnClickListener() {
                    @Override public void onClick(View view)
                    {
                        // check condition
                        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission
                                        .ACCESS_FINE_LOCATION)
                                == PackageManager
                                .PERMISSION_GRANTED
                                && ContextCompat.checkSelfPermission(
                                getActivity(),
                                Manifest.permission
                                        .ACCESS_COARSE_LOCATION)
                                == PackageManager
                                .PERMISSION_GRANTED) {
                            // When permission is granted
                            // Call method
                            getCurrentLocation();
                        }
                        else {
                            // When permission is not granted
                            // Call method
                            requestPermissions(
                                    new String[] {
                                            Manifest.permission
                                                    .ACCESS_FINE_LOCATION,
                                            Manifest.permission
                                                    .ACCESS_COARSE_LOCATION },
                                    100);
                        }
                    }
                });

        return view;
    }
    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(
                requestCode, permissions, grantResults);
        // Check condition
        if (requestCode == 100 && (grantResults.length > 0)
                && (grantResults[0] + grantResults[1]
                == PackageManager.PERMISSION_GRANTED)) {
            // When permission are granted
            // Call  method
            getCurrentLocation();
        }
        else {
            // When permission are denied
            // Display toast
            Toast
                    .makeText(getActivity(),
                            "Permission denied",
                            Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation()
    {
        // Initialize Location manager
        LocationManager locationManager
                = (LocationManager)getActivity().getSystemService(
                        Context.LOCATION_SERVICE);
        // Check condition
        if (locationManager.isProviderEnabled(
                LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER)) {
            // When location service is enabled
            // Get last location
            client.getLastLocation().addOnSuccessListener(
                    new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Initialize location
//                            location = task.getResult();
                            // Check condition
                            if (location != null) {
                                // When location result is not
                                // null set latitude
                                tvLatitude.setText(String.valueOf(location.getLatitude()));
                                // set longitude
                                tvLongitude.setText(String.valueOf(location.getLongitude()));
                            }
                            else {
                                // When location result is null
                                // initialize location request
                                LocationRequest locationRequest
                                        = new LocationRequest()
                                        .setPriority(
                                                LocationRequest
                                                        .PRIORITY_HIGH_ACCURACY)
                                        .setInterval(10000)
                                        .setFastestInterval(1000).setNumUpdates(1);

                                // Initialize location call back
                                LocationCallback locationCallback = new LocationCallback() {
                                    @Override
                                    public void
                                    onLocationResult(LocationResult locationResult)
                                    {
                                        // Initialize
                                        // location
                                        Location location1 = locationResult.getLastLocation();
                                        // Set latitude
                                        tvLatitude.setText(String.valueOf(location1.getLatitude()));
                                        // Set longitude
                                        tvLongitude.setText(String.valueOf(location1.getLongitude()));
                                    }
                                };

                                // Request location updates
                                client.requestLocationUpdates(
                                        locationRequest,
                                        locationCallback,
                                        Looper.myLooper());
                            }
                        }

                    }
//        {
//                        @Override
//                        public void onComplete(
//                                @NonNull Task<Location> task)
//                        {
//
//
//                    }
                    );
        }
        else {
            // When location service is not enabled
            // open location setting
//            startActivity(
//                    new Intent(
//                            Settings
//                                    .ACTION_LOCATION_SOURCE_SETTINGS)
//                            .setFlags(
//                                    Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }
}