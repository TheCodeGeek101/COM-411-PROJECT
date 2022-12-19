//package com.example.smartassaultapplication
//
//import android.Manifest
//import android.annotation.SuppressLint
//import android.app.AlertDialog
//import android.content.Context
//import android.content.pm.PackageManager
//import android.graphics.Bitmap
//import android.graphics.Canvas
//import android.graphics.Color
//import android.location.Location
//import android.os.Bundle
//import android.util.Log
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import com.example.smartassaultapplication.databinding.ActivityMapsBinding
//import com.google.android.gms.location.FusedLocationProviderClient
//import com.google.android.gms.location.LocationServices
//import com.google.android.gms.maps.CameraUpdateFactory
//import com.google.android.gms.maps.GoogleMap
//import com.google.android.gms.maps.OnMapReadyCallback
//import com.google.android.gms.maps.SupportMapFragment
//import com.google.android.gms.maps.model.*
//
//// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//
///**
// * A simple [Fragment] subclass.
// * Use the [MappsFragment.newInstance] factory method to
// * create an instance of this fragment.
// */
//class MappsFragment : Fragment(), ActivityCompat.OnRequestPermissionsResultCallback
//     {
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
//    private var lastKnownLocation: Location? = null
//    private val defaultLocation = LatLng(-33.8523341, 151.2106085)
//    private lateinit var binding: ActivityMapsBinding
//    var mapTypeIcon : ImageView? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//
//        binding= ActivityMapsBinding.inflate(layoutInflater)
//        fusedLocationProviderClient = activity?.let { LocationServices.getFusedLocationProviderClient(it) }!!;
//      }
//
//    @SuppressLint("MissingPermission")
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        // Inflate the layout for this fragment
//        val rootView = inflater.inflate(R.layout.fragment_mapps, container, false)
//        mapTypeIcon = rootView.findViewById(R.id.map_types_icon)
//
//        val mapFragment =
//            childFragmentManager.findFragmentById(R.id.frg) as SupportMapFragment?  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
//        mapFragment!!.getMapAsync { mMap ->
//            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
//            mMap.isMyLocationEnabled = true
//            mMap.clear() //clear old markers
//        }
//        return rootView
//    }
//
//    companion object {
//        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
//        private const val DEFAULT_ZOOM = 15
//        private const val KEY_CAMERA_POSITION = "camera_position"
//        private const val KEY_LOCATION = "location"
//    }
//
//
//     }