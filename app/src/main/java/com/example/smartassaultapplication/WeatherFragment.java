package com.example.smartassaultapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartassaultapplication.databinding.ActivityMainBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;


public class WeatherFragment extends Fragment {

    public WeatherFragment() {
        // Required empty public constructor
    }

    ActivityMainBinding binding;
//    CardView btLocation;
    TextView tvLatitude, tvLongitude,tvtemperature,tvHumidity,tvPressure,tvGroundlevel,tvSealevel,tvCity,tvCountry,tvWeather;
    FusedLocationProviderClient client;
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";
    private static Retrofit retrofit = null;
    private RecyclerView recyclerView = null;
    // insert your themoviedb.org API KEY hereprivate
    final static String API_KEY = "38e96032981e9dd3aea6aaabd9dc1a16";
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
//        btLocation = view.findViewById(R.id.bt_location);
//        tvLatitude = view.findViewById(R.id.tv_latitude);
//        tvLongitude = view.findViewById(R.id.tv_longitude);
        tvtemperature = view.findViewById(R.id.tv_temperature);
        tvHumidity = view.findViewById(R.id.tv_humidity);
        tvPressure = view.findViewById(R.id.tv_pressure);
        tvGroundlevel = view.findViewById(R.id.tv_groundLevel);
        tvSealevel = view.findViewById(R.id.tv_seaLevel);
        tvCity = view.findViewById(R.id.tv_city);
        tvCountry = view.findViewById(R.id.tv_country);
        tvWeather = view.findViewById(R.id.tv_weather);
        


        // Initialize location client
        client = LocationServices.getFusedLocationProviderClient(getActivity());
        // Inflate the layout for this fragment
//        btLocation.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view)
//                    {
//                        //getWeatherUpdate();
//                        // check condition
//                        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission
//                                        .ACCESS_FINE_LOCATION)
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
//                            fetchWeather();
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

    void fetchWeather(){
// Instantiate the RequestQueue.
//        RequestQueue queue = Volley.newRequestQueue(getActivity());
//        String url = "https://api.openweathermap.org/data/2.5/weather?lat="+ lat+"&lon="+lon+"&appid="+ API_KEY;
//
//// Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        String responseData = response.substring(0,500);
//                        System.out.println(responseData);
//                        // Display the first 500 characters of the response string.
////                        textView.setText("Response is: " + response.substring(0,500));
//                    }
//                },
//                new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
////                textView.setText("That didn't work!");
//            }
//        });
//
//// Add the request to the RequestQueue.
//        queue.add(stringRequest);


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
//                                    getWeatherUpdate(location1);
                                    // Set latitude
//                                    tvLatitude.setText(String.valueOf(location1.getLatitude()));
                                    // Set longitude
//                                    tvLongitude.setText(String.valueOf(location1.getLongitude()));
//                                    fetchWeather(location1.getLatitude(),location1.getLongitude());
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
//    public void getWeatherUpdate(){
//
//    }
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        int SDK_INT = Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://api.openweathermap.org/data/2.5/weather?lat=-15.387608&lon=35.33667641&appid="+ API_KEY)
                    .get()
//                .addHeader("lat", "-15.387608")
//                .addHeader("lon", "35.33667641")
//                    .addHeader("appid", API_KEY)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if(response.isSuccessful()){
                    String responseData =response.body().string();
                    System.out.println(responseData);
                    Log.d("Response body:",responseData);

//                    Retrieving the values of temperature, pressure, ground level, sea level, humidity from the Api
                    JSONObject main = new JSONObject(responseData).getJSONObject("main");
                    int temperature = main.getInt("temp");
                    int humidity = main.getInt("humidity");
                    int pressure = main.getInt("pressure");
                    int groundLevel = main.getInt("grnd_level");
                    int seaLevel = main.getInt("sea_level");
                    tvtemperature.setText(String.valueOf(temperature));
                    tvHumidity.setText(String.valueOf(humidity));
                    tvPressure.setText(String.valueOf(pressure));
                    tvGroundlevel.setText(String.valueOf(groundLevel));
                    tvSealevel.setText(String.valueOf(seaLevel));
                    

                    //  Retrieving the weather values
                    JSONArray weather = new JSONObject(responseData).getJSONArray("weather");
                    JSONObject data = weather.getJSONObject(0);
                    String content = data.getString("main");
                    String description = data.getString("description");
                    String icon = data.getString("icon");
                    tvWeather.setText(String.valueOf(content));

                    //  Retrieving the wind values
                    JSONObject wind = new JSONObject(responseData).getJSONObject("wind");
                    int speed = data.getInt("speed");
                    int degrees = data.getInt("deg");
                    int gust = data.getInt("gust");


                    //  Retrieving the cloud values
                    JSONObject cloud = new JSONObject(responseData).getJSONObject("cloud");
                    double all = data.getDouble("all");

                    //  Retrieving the cloud values
                    String visibility = new JSONObject(responseData).getJSONObject("visibility").toString();

                    //  Retrieving details of the area
                    JSONObject sys = new JSONObject(responseData).getJSONObject("sys");
                    int type = sys.getInt("type");
                    String country = sys.getString("country");
                    int sunrise = sys.getInt("sunrise");
                    int sunset = sys.getInt("sunset");
                    tvCountry.setText(String.valueOf(country));

//                    Retrieving the name of the city
                    String city = new JSONObject(responseData).getJSONObject("name").toString();
                    tvCity.setText(String.valueOf(city));
                    System.out.println("THE RESPONSE FROM THE API IN JSON IS:" + sys + "CITY" + city + "visibility" + visibility);
                }
                else{
                    Log.d("Task not successful","error");
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

        }
    }
}