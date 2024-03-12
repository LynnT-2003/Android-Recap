package com.example.androidrecap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidrecap.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {
    private val view: ActivityNotificationBinding by lazy {
        ActivityNotificationBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)
    }
}