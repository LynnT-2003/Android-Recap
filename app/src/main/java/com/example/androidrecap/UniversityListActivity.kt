package com.example.androidrecap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidrecap.adapters.UniversityListAdapter
import com.example.androidrecap.data.api.UniversityApi
import com.example.androidrecap.databinding.ActivityUniversityListBinding
import com.example.androidrecap.models.University
import com.example.androidrecap.viewModels.UniversityViewModel
import kotlinx.coroutines.launch

class UniversityListActivity : AppCompatActivity() {
    // classic
    private val view: ActivityUniversityListBinding by lazy {
        ActivityUniversityListBinding.inflate(
            layoutInflater
        )
    }

    private val universities: MutableList<University> = mutableListOf()

    //    // lifecycle imports (dunno if it was actually needed lol)
//    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
//    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    private val viewModel: UniversityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root) // view.root !

        // ! We wont be directly using the api
        // ! We will be using the ViewModel

//        val api = UniversityApi()
//        // we are going to display this as list/grid
//
//        lifecycleScope.launch {
//            // Coroutine
//            val response = api.getUniversities()
//            Log.d("API Response", response.toString())
//        }

        /*
        1. Call API
        1.1 Create and Define Adapter
        2. Get the list of university and put it in RV
        3. Add Adapter in RV
         */

        viewModel.getUniversities() // 1. Call API

        view.rvUniversities.adapter = UniversityListAdapter(universities) // 1.1, 3  Define Adapter
        view.rvUniversities.layoutManager = LinearLayoutManager(this)

        // Implementing Swipe Refresh
        view.swipeLayout.setOnRefreshListener {
            viewModel.getUniversities()
        }

        // observe for changes in VM as explained
        // we got the values, now display the values to RV
        // 2. Get the list of university and put it in RV
        viewModel.universities.observe(this) { universityList ->
            Log.d("DAT", "UI Updated from Swipe Refresh")

            // display values to rv
            universities.clear()
            universities.addAll(universityList) // update the list of universities
            // tell adapter that values has been updated, render everything again please
            view.rvUniversities.adapter?.notifyDataSetChanged() // adapter cannot be null
        }

        viewModel.isLoading.observe(this) { isLoading ->
            view.swipeLayout.isRefreshing = isLoading
        }
    }
}