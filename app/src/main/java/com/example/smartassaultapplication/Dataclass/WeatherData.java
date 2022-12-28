package com.example.smartassaultapplication.Dataclass;

import com.google.gson.annotations.SerializedName;

public class WeatherData {
    @SerializedName("name")
    private String superName;


    public WeatherData(String name) {
        this.superName = name;
    }

    public String getName() {
        return superName;
    }
}
