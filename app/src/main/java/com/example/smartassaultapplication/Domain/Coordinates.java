package com.example.smartassaultapplication.Domain;

public class Coordinates {
    public double latitude;
    public double longitude;

    public Coordinates() {
        // Default constructor required for calls to DataSnapshot.getValue(Coordinate.class)
    }

    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

//    public Coordinates(Builder builder){
//
//    }

}
