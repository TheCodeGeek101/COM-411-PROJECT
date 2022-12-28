package com.example.smartassaultapplication.Fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartassaultapplication.Activities.NewsFeedActivity;
import com.example.smartassaultapplication.Adapters.NewsAdapter;
import com.example.smartassaultapplication.Adapters.NewsData;
import com.example.smartassaultapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

CardView newsFeed;
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private List<NewsData> newsDataList = new ArrayList<>();
    private ImageView imageView;
    private static final String API_KEY = "a9e7c985a2c84ee096d0068464b7d39d";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_home, container, false);

        newsFeed = view.findViewById(R.id.news_feed);
        newsFeed.setOnClickListener(view1 -> redirectToNewsFeed());
        // Inflate the layout for this
        return inflater.inflate(R.layout.fragment_home, container, false);


    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        int SDK_INT = Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

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


                    //  Retrieving the feed values
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

            } catch ( IOException | JSONException jsonException) {
                jsonException.printStackTrace();
            }
        }
    }
    private void redirectToNewsFeed() {
     Intent intent = new Intent(getActivity(), NewsFeedActivity.class);
     startActivity(intent);
    }
}