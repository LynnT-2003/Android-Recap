package com.example.androidrecap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidrecap.databinding.ActivityCameraBinding

class CameraActivity : AppCompatActivity() {
    val view: ActivityCameraBinding by lazy {ActivityCameraBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)
    }
}