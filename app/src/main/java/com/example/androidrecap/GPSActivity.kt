package com.example.androidrecap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidrecap.databinding.ActivityGpsactivityBinding

class GPSActivity : AppCompatActivity() {
    val view: ActivityGpsactivityBinding by lazy { ActivityGpsactivityBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)
    }
}