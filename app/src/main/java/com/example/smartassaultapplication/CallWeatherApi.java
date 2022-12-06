//package com.example.smartassaultapplication;
//
//import android.util.Log;
//
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//
////import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequest;
//
//import java.io.IOException;
//
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//public class CallWeatherApi {
//
//    HttpRequest request = HttpRequest.newBuilder()
//            .uri(URI.create("https://weatherbit-v1-mashape.p.rapidapi.com/current?lon=38.5&lat=-78.5"))
//            .header("X-RapidAPI-Key", "2aedfdb066msh390ca16b89d94c2p1c60e3jsn70ba6f92dcca")
//            .header("X-RapidAPI-Host", "weatherbit-v1-mashape.p.rapidapi.com")
//            .method("GET", HttpRequest.BodyPublishers.noBody())
//            .build();
//    HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//System.out.println(response.body());
//
//}
