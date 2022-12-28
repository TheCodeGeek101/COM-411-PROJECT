package com.example.smartassaultapplication.Tests;

import static org.junit.Assert.assertTrue;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestWeatherApi {
    double lat = -15.3875913;
    double lon = 35.3367807;
    final static String API_KEY = "38e96032981e9dd3aea6aaabd9dc1a16";

    @Test
    public void testWeatherAPI() throws IOException, JSONException {
        // Create a connection to the API endpoint
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?lat="+ lat +"&lon="+ lon +"&appid="+ API_KEY);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        // Read the response from the API
        InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
        BufferedReader reader = new BufferedReader(streamReader);
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        streamReader.close();

        // Parse the response to extract the temperature
        JSONObject json = new JSONObject(response.toString());
        JSONObject main = json.getJSONObject("main");
        double temperature = main.getDouble("temp");

        // Verify that the temperature is within a certain range
//        assertTrue();
        assertTrue(temperature > 280 && temperature < 300);
    }

}
