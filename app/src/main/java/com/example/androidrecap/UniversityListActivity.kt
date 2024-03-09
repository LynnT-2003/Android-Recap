package com.example.androidrecap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.androidrecap.data.api.UniversityApi
import kotlinx.coroutines.launch

class UniversityListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_university_list)

        val api = UniversityApi()

        lifecycleScope.launch {
            // Coroutine
            val response = api.getUniversities()
            Log.d("API Response", response.toString())
        }
    }
}