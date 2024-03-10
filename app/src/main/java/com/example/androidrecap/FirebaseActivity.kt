package com.example.androidrecap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidrecap.databinding.ActivityFirebaseBinding

class FirebaseActivity : AppCompatActivity() {
    private val view: ActivityFirebaseBinding by lazy {
        ActivityFirebaseBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)
    }
}