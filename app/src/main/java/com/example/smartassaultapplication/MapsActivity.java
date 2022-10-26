package com.example.smartassaultapplication;//package com.example.smartassaultapplication;

import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.location.Location;
import android.os.Bundle;

import com.example.smartassaultapplication.domain.GeofenceHelper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.smartassaultapplication.databinding.ActivityMapsBinding;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private static final int ACCESS_REQUEST_CODE = 10001;
    private static final String GEOFENCE_ID = "client-123" ;
    private static final int BACKGROUND_LOCATION_REQUEST_CODE =10002 ;
    private GeofencingClient geofencingClient;
    private GeofenceHelper geofenceHelper;
    private Location location;
//    private ActivityMapsBinding binding;
    private static  final String TAG = "MapsActivity";
    Marker userLocationMarker;
    PendingIntent pendingIntent;
    private float GEOFENCE_RADIUS = 30;
    FusedLocationProviderClient fusedLocationProviderClient;
    ArrayList<Geofence> geofenceList = new ArrayList<Geofence>();
    Geofence geofence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        mMap.setMyLocationEnabled(true);
//        LocationCallback locationCallback = new LocationCallback() {
//            @Override
//            public void onLocationResult(LocationResult locationResult){
//                super.onLocationResult(locationResult);
//                Log.d(TAG,"OnLocationResult: " + locationResult.getLastLocation());
//                if(mMap != null){
//                    setUserLocationMarker(locationResult.getLastLocation());
//                }
//            }
//        };
////        mMap.clear();
//        enableUserLocation();
//        mMap.setMyLocationEnabled(true);
//        mMap.setOnMapLongClickListener(this);

    }

//    @SuppressLint("MissingPermission")
//    private void enableUserLocation() {
//    if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
//        fusedLocationProviderClient.getLastLocation()
//                .addOnSuccessListener(location -> {
//                    if(location != null){
//                    Geocoder geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());
//                        try {
//                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),2);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    else
//                    {
//                        askPermission();
//                    }
//                });
//    }
//
//    }

//    private void askPermission() {
//        ActivityCompat.requestPermissions(MapsActivity.this,new String[]{
//                Manifest.permission.ACCESS_FINE_LOCATION
//        },ACCESS_REQUEST_CODE);
//
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if(requestCode == ACCESS_REQUEST_CODE)
//        {
//            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                enableUserLocation();
//            }
//        }
//        else
//        {
//            Toast.makeText(MapsActivity.this,"Location permission required",Toast.LENGTH_LONG).show();
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//    }
}