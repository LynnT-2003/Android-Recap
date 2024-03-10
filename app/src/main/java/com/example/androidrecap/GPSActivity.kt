package com.example.androidrecap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.androidrecap.databinding.ActivityGpsactivityBinding
import com.example.androidrecap.utils.PermissionHandler
import com.example.androidrecap.viewModels.GPSViewModel

class GPSActivity : AppCompatActivity() {
    private val view: ActivityGpsactivityBinding by lazy { ActivityGpsactivityBinding.inflate(layoutInflater) }
    private val viewModel: GPSViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)
        PermissionHandler.requestPermissionIfRequired(this, PermissionHandler.GPS)

        view.btnGetLocation.setOnClickListener {
            viewModel.getCurrentLocation()
        }

        viewModel.userLocation.observe(this) {
            view.tvUserLocation.text = "User Location: ${it.latitude} ${it.longitude}"
        }

        viewModel.error.observe(this) {
            AlertDialog.Builder(this)
                .setTitle("Something went wrong, are you on Jupiter?")
                .setMessage(it.message)
                .setPositiveButton("Ok") {dialog, _ -> dialog.cancel()}
                .show()
        }
    }
}