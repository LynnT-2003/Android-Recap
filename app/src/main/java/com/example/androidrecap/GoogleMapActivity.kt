package com.example.androidrecap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.viewModels
import com.example.androidrecap.databinding.ActivityGoogleMapBinding
import com.example.androidrecap.utils.PermissionHandler
import com.example.androidrecap.viewModels.GPSViewModel
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng

class GoogleMapActivity : AppCompatActivity() {
    private val view: ActivityGoogleMapBinding by lazy {
        ActivityGoogleMapBinding.inflate(
            layoutInflater
        )
    }
    private val viewModel: GPSViewModel by viewModels()
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)
        PermissionHandler.requestPermissionIfRequired(this, PermissionHandler.GPS)
        viewModel.getCurrentLocation()

        view.mvGoogleMap.onCreate(savedInstanceState)

        view.mvGoogleMap.getMapAsync {
            map = it
        }

        viewModel.userLocation.observe(this@GoogleMapActivity) {
            Log.d("DAT", "TRIGGEr")
            val latLng = LatLng(it.latitude, it.longitude)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 0f))
        }
    }

    override fun onStart() {
        super.onStart()
        view.mvGoogleMap.onStart()
    }

    override fun onResume() {
        super.onResume()
        view.mvGoogleMap.onResume()
    }

    override fun onPause() {
        super.onPause()
        view.mvGoogleMap.onResume()
    }

    override fun onStop() {
        super.onStop()
        view.mvGoogleMap.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        view.mvGoogleMap.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        view.mvGoogleMap.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        view.mvGoogleMap.onSaveInstanceState(outState)
    }

}