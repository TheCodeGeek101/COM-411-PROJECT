//package com.example.smartassaultapplication.Tests;
//
//import static android.content.Context.LOCATION_SERVICE;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
//import android.annotation.SuppressLint;
//import android.location.Location;
//import android.location.LocationManager;
//
//import org.junit.Test;
//
//public class TestCurrentLocation {
//    @Test
//    public void testGetCurrentLocation() {
//        // Request the current location
//        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//
//        // Verify that the location was retrieved successfully
//        assertNotNull(location);
//
//        // Extract the latitude and longitude
//        double latitude = location.getLatitude();
//        double longitude = location.getLongitude();
//
//        // Verify that the latitude and longitude are within a certain range
//        assertTrue(latitude > -90 && latitude < 90);
//        assertTrue(longitude > -180 && longitude < 180);
//    }
//}
