package com.example.smartassaultapplication.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.smartassaultapplication.Activities.MapsActivity;
import com.example.smartassaultapplication.R;
import com.example.smartassaultapplication.Dataclass.Coordinates;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LocationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FirebaseDatabase database;
    DatabaseReference ref;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button btnMaps;
    public LocationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LocationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LocationFragment newInstance(String param1, String param2) {
        LocationFragment fragment = new LocationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_location, container, false);
        btnMaps = view.findViewById(R.id.bt_maps);
        btnMaps.setOnClickListener(view1 -> MoveToMaps());
//         database= FirebaseDatabase.getInstance();
//         ref = database.getReference("coordinates");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location, container, false);
    }

    private void MoveToMaps() {
        Random rand = new Random();

        // Generate a random latitude coordinate
        double lat = (rand.nextDouble() * 180) - 90;

        // Generate a random longitude coordinate
        double lng = (rand.nextDouble() * 360) - 180;
//        LatLng latLng = new LatLng(lat,lng);
        Coordinates coordinates = new Coordinates(lat,lng);

        // Save the coordinate to the database
//        ref.setValue(coordinates);
        Intent intent = new Intent(getActivity(), MapsActivity.class);
        startActivity(intent);
    }
}