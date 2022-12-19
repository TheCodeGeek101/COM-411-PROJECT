package com.example.smartassaultapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartassaultapplication.domain.NewsAdapter;
import com.example.smartassaultapplication.domain.NewsData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://newsapi.org/v2/everything?q=tesla&from=2022-11-18&sortBy=publishedAt&apiKey=" + API_KEY)
                        .get()
                        .build();
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    System.out.println(responseData);
                    Log.d("Response body:", responseData);


                    //  Retrieving the weather values
                    JSONArray articles = new JSONObject(responseData).getJSONArray("articles");
                    System.out.println("-----------------THE ARTICLES ARRAY RETRIEVED ARE:::::::"+ articles);
                    for (int i = 1; i < articles.length(); i++) {
                        JSONObject data = articles.getJSONObject(i);
                        System.out.println(data);
                        NewsData newsData = new NewsData(
                                data.getString("title"),
                                data.getString("description"),
                                data.getString("url"),
                                data.getString("urlToImage"),
                                imageView);
                        newsDataList.add(newsData);
                        String title = data.getString("title");
                        String description = data.getString("description");
                        String url = data.getString("url");
                        String urlToImage = data.getString("urlToImage");
                        String author = data.getString("author");
                    }
                } else {
                    Log.d("Task not successful", "error");
                }

            } catch (JSONException | IOException jsonException) {
                jsonException.printStackTrace();
            }

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