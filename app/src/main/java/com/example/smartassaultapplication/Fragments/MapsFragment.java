package com.example.smartassaultapplication.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.smartassaultapplication.Helpers.GeofenceHelper;
import com.example.smartassaultapplication.R;
import com.example.smartassaultapplication.Services.GeofenceBroadcastReceiver;
import com.example.smartassaultapplication.databinding.FragmentMapsBinding;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.ArrayList;
import java.util.List;


public class MapsFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {
    FragmentMapsBinding binding;
    SupportMapFragment mapFragment;
    private GoogleMap mMap;
//    private static final String TAG = MapsActivityCurrentPlace.class.getSimpleName();
    private GoogleMap map;
    private CameraPosition cameraPosition;

    // The entry point to the Places API.
    private PlacesClient placesClient;

    // The entry point to the Fused Location Provider.
    private FusedLocationProviderClient client;

    // A default location (Sydney, Australia) and default zoom to use when location permission is
    // not granted.
    private final LatLng defaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean locationPermissionGranted;

    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private Location lastKnownLocation;

    // Keys for storing activity state.
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";

    // Used for selecting the current place.
    private static final int M_MAX_ENTRIES = 5;
    private String[] likelyPlaceNames;
    private String[] likelyPlaceAddresses;
    private List[] likelyPlaceAttributions;
    private static  final String TAG = "MapsFragment";
    private LatLng[] likelyPlaceLatLngs;
    private static final int ACCESS_REQUEST_CODE = 10001;
    private static final String GEOFENCE_ID = "client-123" ;
    private static final int BACKGROUND_LOCATION_REQUEST_CODE =10002 ;
    private GeofencingClient geofencingClient;
    private GeofenceHelper geofenceHelper;
    Marker mCurrLocationMarker;
    private final int requestPermissionCode = 723;
    Button btnLocation;
    Marker userLocationMarker;
    PendingIntent pendingIntent;
    private float GEOFENCE_RADIUS = 30;
    FusedLocationProviderClient fusedLocationProviderClient;
    ArrayList<Geofence> geofenceList = new ArrayList<Geofence>();
    Geofence geofence;
    public MapsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve location and camera position from saved instance state.
        if (savedInstanceState != null) {
            lastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            cameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }
    }




    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @NonNull ViewGroup container,@NonNull  Bundle savedInstanceState) {
        // Initialize view

        View view=inflater.inflate(R.layout.fragment_maps, container, false);
        client = LocationServices.getFusedLocationProviderClient(
                        getActivity());
        geofencingClient = LocationServices.getGeofencingClient(getActivity());
//         btnLocation = view.findViewById(R.id.locationButton);
//        Button button = view.findViewById(R.id.button_current_location);
//        button.setVisibility(View.VISIBLE);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Center the map on the device's current location
////                getDeviceLocation();
//            }
//        });

//        btnLocation.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override public void onClick(View view)
//                    {
//                        // check condition
//                        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission
//                                .ACCESS_FINE_LOCATION)
//                                == PackageManager
//                                .PERMISSION_GRANTED
//                                && ContextCompat.checkSelfPermission(
//                                getActivity(),
//                                Manifest.permission
//                                        .ACCESS_COARSE_LOCATION)
//                                == PackageManager
//                                .PERMISSION_GRANTED) {
//                            // When permission is granted
//                            // Call method
//                            getCurrentLocation();
//                        }
//                        else {
//                            // When permission is not granted
//                            // Call method
//                            requestPermissions(
//                                    new String[] {
//                                            Manifest.permission
//                                                    .ACCESS_FINE_LOCATION,
//                                            Manifest.permission
//                                                    .ACCESS_COARSE_LOCATION },
//                                    100);
//                        }
//                    }
//                });
        // Initialize map fragment
        SupportMapFragment supportMapFragment=(SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);

        // Async map
        supportMapFragment.getMapAsync(googleMap -> {
            // When map is loaded
            googleMap.setOnMapClickListener(latLng -> {
                // When clicked on map
                // Initialize marker options
                MarkerOptions markerOptions=new MarkerOptions();
                // Set position of marker
                markerOptions.position(latLng);
                // Set title of marker
                markerOptions.title(latLng.latitude+" : "+latLng.longitude);
                // Remove all marker
                googleMap.clear();
                // Animating to zoom the marker
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                // Add marker on map
                googleMap.addMarker(markerOptions);
            });
        });

                mapInitialize();

        return view;

    }





    private void mapInitialize() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setSmallestDisplacement(16);
        locationRequest.setFastestInterval(3000);


//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(final GoogleMap map) {
        this.map=map;
        getCurrentLocation();

        map.setOnInfoWindowClickListener((GoogleMap.OnInfoWindowClickListener) getActivity());
        map.setMyLocationEnabled(true);
//        locMgr=(LocationManager)getSystemService(LOCATION_SERVICE);
//        crit.setAccuracy(Criteria.ACCURACY_FINE);
//        follow();

        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult){
                super.onLocationResult(locationResult);
                Log.d(TAG,"OnLocationResult: " + locationResult.getLastLocation());
                if(mMap != null){
                    setUserLocationMarker(locationResult.getLastLocation());
                }

            }
        };


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
    private void setUserLocationMarker(Location location){
        LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());

        if(userLocationMarker == null){
            MarkerOptions markerOptions = new MarkerOptions().position(latLng);
            userLocationMarker = mMap.addMarker(markerOptions);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));
        }
        else{
//            use the previous created marker
            userLocationMarker.setPosition(latLng);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));
        }
    }
    private void zoomToUserLocation(){
        @SuppressLint("MissingPermission")
        Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();

        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));
//                        mMap.addMarker(new MarkerOptions().position(latLng));
            }
        });

        locationTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

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
            client.getLastLocation().addOnCompleteListener(
                    task -> {
                        // Initialize location
                        Location location = task.getResult();
                        // Check condition
                        if (location != null) {
                            // When location result is not
                            // null set latitude
//                            tvLatitude.setText(String.valueOf(location.getLatitude()));
                            // set longitude
//                            tvLongitude.setText(String.valueOf(location.getLongitude()));
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
                                    LatLng latLng = new LatLng(location1.getLatitude(), location1.getLongitude());
                                    MarkerOptions markerOptions = new MarkerOptions();
                                    markerOptions.position(latLng);
                                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                                    mCurrLocationMarker = mMap.addMarker(markerOptions);
                                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                                    mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
                                    // Set latitude
//                                    tvLatitude.setText(String.valueOf(location1.getLatitude()));
                                    // Set longitude
//                                    tvLongitude.setText(String.valueOf(location1.getLongitude()));
                                }
                            };

                            // Request location updates
                            client.requestLocationUpdates(
                                    locationRequest,
                                    locationCallback,
                                    Looper.myLooper());
                        }
                    });
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

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        if(Build.VERSION.SDK_INT >= 29){
//                    ask for permisssion
            if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED){
                createGeofence(latLng);
            }
            else{
                if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.ACCESS_BACKGROUND_LOCATION)){

                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION},
                            BACKGROUND_LOCATION_REQUEST_CODE);

                }
                else{
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION},
                            BACKGROUND_LOCATION_REQUEST_CODE);
                }
            }
        }
        else{
            createGeofence(latLng);
        }
    }
    public void createGeofence(LatLng latLng){
        mMap.clear();
        addMaker(latLng);
        addCircle(latLng,GEOFENCE_RADIUS);
        addGeofence(latLng,GEOFENCE_RADIUS);

    }

    //Adding a Geofence
    @SuppressLint("MissingPermission")
    public void addGeofence(LatLng latLng, float radius){
//        geofence = geofenceHelper.getGeofence(GEOFENCE_ID,latLng, radius, Geofence.GEOFENCE_TRANSITION_ENTER);

        geofenceList.add(
                new Geofence.Builder()
                        .setCircularRegion(latLng.latitude,latLng.longitude,radius)
                        .setRequestId(GEOFENCE_ID)
                        .setLoiteringDelay(5000)
                        .setExpirationDuration(Geofence.NEVER_EXPIRE)
                        .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER |
                                Geofence.GEOFENCE_TRANSITION_EXIT)
                        .build()
        );

        getGeofencingRequest();
        getPendingIntent();


        geofencingClient.addGeofences(getGeofencingRequest(),getPendingIntent())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG,"onSuccess: Geofences added...");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String errorMessage = geofenceHelper.getErrorString(e);
                        final int d = Log.d(TAG, "onFailure" + errorMessage);
                    }
                });
    }


    private void addMaker(LatLng latLng){
        MarkerOptions markerOptions = new MarkerOptions().position(latLng);
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,16));
    }
    private void addCircle(LatLng latLng, float radius){
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(latLng);
        circleOptions.radius(radius);
        circleOptions.strokeColor(Color.argb(255,255,0,0));
        circleOptions.fillColor(Color.argb(64,255,0,0));
        circleOptions.strokeWidth(4);
        mMap.addCircle(circleOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,16));
    }
    public GeofencingRequest getGeofencingRequest(){
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofences(geofenceList);
        return builder.build();


    }
    public PendingIntent getPendingIntent(){

        if(pendingIntent != null){
            return pendingIntent;
        }

        Intent intent = new Intent(getActivity(), GeofenceBroadcastReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getActivity(),2607,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        return pendingIntent;
    }


//    public boolean checkPermission() {
//        int firstPermissionResult = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);
//        int secondPermissionResult = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION);
//        return firstPermissionResult == PackageManager.PERMISSION_GRANTED && secondPermissionResult == PackageManager.PERMISSION_GRANTED;
//    }
//
//    private void requestPermission() {
//        requestPermissions(
//                new String[] {
//                        Manifest.permission.ACCESS_FINE_LOCATION,
//                        Manifest.permission.ACCESS_COARSE_LOCATION
//                },
//                requestPermissionCode
//        );
//    }

}