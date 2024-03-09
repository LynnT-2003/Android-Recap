package com.example.androidrecap.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidrecap.data.api.UniversityApi
import com.example.androidrecap.models.University
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// ViewModel is the middleman
// View is listening to changes on the ViewModel

class UniversityViewModel : ViewModel() {
    // https://upload.wikimedia.org/wikipedia/commons/thumb/8/87/MVVMPattern.png/500px-MVVMPattern.png
    private val api = UniversityApi()
    val universities: MutableLiveData<List<University>> = MutableLiveData()

    // (1). function to call data
    // (2). update data to middleman here

    fun getUniversities() { // (1). function to call data
        // IO used in short connections outside application (API Call, Caching, etc.)
        // Main used for long connections
        viewModelScope.launch(Dispatchers.IO) {
            // response is a List of University Model Object
            val response = api.getUniversities() // call the API here
            universities.postValue(response) // (2). update data to middleman here
        }
    }
}