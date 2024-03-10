package com.example.androidrecap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidrecap.databinding.ActivityGoogleMapBinding
import com.google.android.gms.maps.GoogleMap

class GoogleMapActivity : AppCompatActivity() {
    private val view: ActivityGoogleMapBinding by lazy {
        ActivityGoogleMapBinding.inflate(
            layoutInflater
        )
    }
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)

    }
}