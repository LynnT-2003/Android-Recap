package com.example.androidrecap

import android.app.Application
import com.example.androidrecap.utils.NotificationHandler

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        NotificationHandler.initNotificationChannel(this)
    }
}