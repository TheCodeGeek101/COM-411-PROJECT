package com.example.smartassaultapplication.Activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartassaultapplication.Adapters.NewsAdapter;
import com.example.smartassaultapplication.Adapters.NewsData;
import com.example.smartassaultapplication.R;

import java.util.ArrayList;
import java.util.List;

public class NewsFeedActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private List<NewsData> newsDataList = new ArrayList<>();
    private ImageView imageView;

    private static final String API_KEY = "a9e7c985a2c84ee096d0068464b7d39d";

    public NewsFeedActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_news_feed);
        new UpdateTask().execute();
    }

    private class UpdateTask extends AsyncTask<String, String, String> {
        protected String doInBackground(String... urls) {


            return null;
        }

        void getNewsFeed() {


        }
    }
}



//    String url = "";
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                recyclerView = findViewById(R.id.recycler_view);
//                imageView = findViewById(R.id.imageView);
//                newsAdapter = new NewsAdapter(newsDataList);
//                RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(), 1);
//                recyclerView.setLayoutManager(manager);
//                recyclerView.setAdapter(newsAdapter);
//                JSONArray jsonArray = null;
//
//        try {
//            JSONArray weather = new JSONObject(responseData).getJSONArray("weather");
//            JSONObject data = weather.getJSONObject(0);
//            String content = data.getString("main");
//            String description = data.getString("description");
//            String icon = data.getString("icon");
//            tvWeather.setText(content);
//
////                    jsonArray = response.getJSONArray("articles");
//            NewsData newsData = new NewsData(
//                    jsonArray.getJSONObject(0).get("title").toString(),
//                    jsonArray.getJSONObject(0).get("description").toString(),
//                    jsonArray.getJSONObject(0).get("url").toString(),
//                    jsonArray.getJSONObject(0).get("urlToImage").toString(), imageView);
//
//            for (int i = 1; i < jsonArray.length(); i++) {
//                JSONObject objectInArray = jsonArray.getJSONObject(i);
//                newsData = new NewsData(
//                        objectInArray.get("title").toString(),
//                        objectInArray.get("description").toString(),
//                        objectInArray.get("url").toString(),
//                        objectInArray.get("urlToImage").toString(),
//                        imageView);
//                newsDataList.add(newsData);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }, new Response.ErrorListener(){
//
//    }
//});