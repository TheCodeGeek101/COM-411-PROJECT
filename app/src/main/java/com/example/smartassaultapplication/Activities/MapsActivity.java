package com.example.smartassaultapplication.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.smartassaultapplication.Helpers.GeofenceHelper;
import com.example.smartassaultapplication.R;
import com.example.smartassaultapplication.Services.GeofenceBroadcastReceiver;
import com.example.smartassaultapplication.databinding.ActivityMaps2Binding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {
//    FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

    private GoogleMap mMap;
    private final ThreadLocal<ActivityMaps2Binding> binding = new ThreadLocal<ActivityMaps2Binding>();
    private FusedLocationProviderClient fusedLocationClient;
    private static final int ACCESS_REQUEST_CODE = 10001;
    private static final String GEOFENCE_ID = "client-123";
    private static final int BACKGROUND_LOCATION_REQUEST_CODE = 10002;
    private GeofencingClient geofencingClient;
    private GeofenceHelper geofenceHelper;
    //    private GoogleMap mMap;
    private Location location;
    //    private ActivityMapsBinding binding;
    private static final String TAG = "MapsActivity";
    Marker userLocationMarker;
    PendingIntent pendingIntent;
    private float GEOFENCE_RADIUS = 30;
    FusedLocationProviderClient fusedLocationProviderClient;
    ArrayList<Geofence> geofenceList = new ArrayList<Geofence>();
    Geofence geofence;
//    FirebaseDatabase database;
//    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding.set(ActivityMaps2Binding.inflate(getLayoutInflater()));
        setContentView(binding.get().getRoot());
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//        database = FirebaseDatabase.getInstance();
//        ref = database.getReference("coordinates");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
//    @SuppressLint("MissingPermission")
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Coordinates coord = dataSnapshot.getValue(Coordinates.class);
//
//                // Add a marker to the map at the coordinate
//                LatLng latLng = new LatLng(coord.latitude, coord.longitude);
//                mMap.addMarker(new MarkerOptions().position(latLng).title("Coordinate"));
//
//                // Move the camera to the coordinate
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//                PolylineOptions options = new PolylineOptions().add(latLng).color(Color.BLUE).width(10);
//                Polyline polyline = mMap.addPolyline(options);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        mMap.setMapStyle()
        // Add a marker in Sydney and move the camera

//      LatLng sydney = new LatLng(-34, 151);

        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    // Update the map with the new location

                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    googleMap.addMarker(new MarkerOptions().position(latLng));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("locations");
//                    databaseReference.setValue(location);
                }
            }
        };
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000); // Set the interval between location updates to 10 seconds
        locationRequest.setFastestInterval(5000); // Set the fastest interval between location updates to 5 seconds
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY); // Set the location accuracy to high

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);


        //    creating a location callback
//        LocationCallback locationCallback = new LocationCallback() {
//            @Override
//            public void onLocationResult(LocationResult locationResult){
//                super.onLocationResult(locationResult);
//                Log.d(TAG,"OnLocationResult: " + locationResult.getLastLocation());
//                if(mMap != null){
//                    setUserLocationMarker(locationResult.getLastLocation());
//                }
//
//            }
//        };
//        mMap.clear();
        enableUserLocation();
//        mMap.setOnMapLongClickListener(this);
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @SuppressLint("MissingPermission")
    private void enableUserLocation() {
//            check if permission is granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
//                zoomToUserLocation();

        } else {
//                Ask for Permission
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
//                    show a dialog of why the permission is needed
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        ACCESS_REQUEST_CODE);
            } else {
//                     Directly ask the permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        ACCESS_REQUEST_CODE);
            }
        }
    }

    //    @SuppressLint({"MissingSuperCall", "MissingPermission"})
    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == ACCESS_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mMap.setMyLocationEnabled(true);
            } else {

            }
        }
        if (requestCode == BACKGROUND_LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "The Geofence for your compound has been added successifully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Could not establish geofence due to lack of background access location permission...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //    adding a marker to the users location
//    private void setUserLocationMarker(Location location){
//        LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
//
//        if(userLocationMarker == null){
//            MarkerOptions markerOptions = new MarkerOptions().position(latLng);
//            userLocationMarker = mMap.addMarker(markerOptions);
//            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));
//        }
//        else{
////            use the previous created marker
//            userLocationMarker.setPosition(latLng);
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));
//        }
//    }
//    private void zoomToUserLocation(){
//        @SuppressLint("MissingPermission")
//        Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
//
//        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
//            @Override
//            public void onSuccess(Location location) {
//                LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
//                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));
////                        mMap.addMarker(new MarkerOptions().position(latLng));
//            }
//        });
//
//        locationTask.addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//            }
//        });
//
//    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {

        if(Build.VERSION.SDK_INT >= 29){
//                    ask for permisssion
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED){
                createGeofence(latLng);
            }
            else{
                if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_BACKGROUND_LOCATION)){

                    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION},
                            BACKGROUND_LOCATION_REQUEST_CODE);

                }
                else{
                    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION},
                            BACKGROUND_LOCATION_REQUEST_CODE);
                }
            }
        }
        else{
            createGeofence(latLng);
        }
//
    }

    public void createGeofence(LatLng latLng) {
        mMap.clear();
        addMaker(latLng);
        addCircle(latLng, GEOFENCE_RADIUS);
        addGeofence(latLng, GEOFENCE_RADIUS);

    }

    //Adding a Geofence
//    @SuppressLint("MissingPermission")

    @SuppressLint("MissingPermission")
    public void addGeofence(LatLng latLng, float radius) {
//        geofence = geofenceHelper.getGeofence(GEOFENCE_ID,latLng, radius, Geofence.GEOFENCE_TRANSITION_ENTER);

        geofenceList.add(
                new Geofence.Builder()
                        .setCircularRegion(latLng.latitude, latLng.longitude, radius)
                        .setRequestId(GEOFENCE_ID)
                        .setLoiteringDelay(5000)
                        .setExpirationDuration(Geofence.NEVER_EXPIRE)
                        .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER |
                                Geofence.GEOFENCE_TRANSITION_EXIT)
                        .build()
        );

        getGeofencingRequest();
        getPendingIntent();


//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},ACCESS_REQUEST_CODE);


        } else {
            // Permission has already been granted, you can call addGeofences now
            geofencingClient.addGeofences(getGeofencingRequest(), getPendingIntent())
                    .addOnSuccessListener(aVoid -> Log.d(TAG, "onSuccess: Geofences added..."))
                    .addOnFailureListener(e -> {
                        String errorMessage = geofenceHelper.getErrorString(e);
                        final int d = Log.d(TAG, "onFailure" + errorMessage);
                    });
        }


        }


    private void addMaker(LatLng latLng) {
        MarkerOptions markerOptions = new MarkerOptions().position(latLng);
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
    }

    private void addCircle(LatLng latLng, float radius) {
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(latLng);
        circleOptions.radius(radius);
        circleOptions.strokeColor(Color.argb(255, 255, 0, 0));
        circleOptions.fillColor(Color.argb(64, 255, 0, 0));
        circleOptions.strokeWidth(4);
        mMap.addCircle(circleOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
    }

    public GeofencingRequest getGeofencingRequest() {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofences(geofenceList);
        return builder.build();
    }

    public PendingIntent getPendingIntent() {
        if (pendingIntent != null) {
            return pendingIntent;
        }
        Intent intent = new Intent(this, GeofenceBroadcastReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 2607, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Display the location on the map
                                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                                mMap.addMarker(new MarkerOptions().position(latLng).title("Current location"));
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                            }
                        }
                );
        }
}