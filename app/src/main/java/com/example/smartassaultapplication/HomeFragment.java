package com.example.smartassaultapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

CardView newsFeed;


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

    private void redirectToNewsFeed() {
     Intent intent = new Intent(getActivity(),NewsFeedActivity.class);
     startActivity(intent);
    }
}