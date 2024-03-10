package com.example.androidrecap.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidrecap.data.api.UniversityApi
import com.example.androidrecap.data.cache.UniversityCache
import com.example.androidrecap.models.University
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// ViewModel is the middleman
// View is listening to changes on the ViewModel

class UniversityViewModel(app: Application) : AndroidViewModel(app) {
    // Change type, app is now our context (Auto Assignment)
    // https://upload.wikimedia.org/wikipedia/commons/thumb/8/87/MVVMPattern.png/500px-MVVMPattern.png
    private val api = UniversityApi()
    private val cache = UniversityCache(app)

    val universities: MutableLiveData<List<University>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()


    // (1). function to call data
    // (2). update data to middleman here

    fun getUniversities() { // (1). function to call data
        // IO used in short connections outside application (API Call, Caching, etc.)
        // Main used for long connections
        showLoading()

        // check cache
        if (cache.hasUniversities()) {
            Log.d("DAT", "Cache is available")
            val response = cache.getUniversities()
            universities.postValue(response)
        } else {
            Log.d("DAT", "Cache is not available, calling API..")
            viewModelScope.launch(Dispatchers.IO) {
                // response is a List of University Model Object
                val response = api.getUniversities() // call the API here
                cache.setUniversities(response)
                universities.postValue(response) // (2). update data to middleman here
            }
        }

        dismissLoading()
    }

    private fun showLoading() {
        isLoading.postValue(true)
    }

    private fun dismissLoading() {
        isLoading.postValue(false)
    }
}