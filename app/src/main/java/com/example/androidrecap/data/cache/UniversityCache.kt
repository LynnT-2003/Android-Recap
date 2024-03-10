package com.example.androidrecap.data.cache

import android.content.Context
import com.example.androidrecap.models.University
import com.google.gson.Gson

class UniversityCache(private val context: Context) {
    private val name = "university"
    private val gson = Gson()
    private val cache = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    fun hasUniversities(): Boolean {
        return cache.contains("universityList")
    }

    fun clearUniversities() {
        // clear cache
        cache.edit().apply{
            clear()
            apply()
        }
    }

    fun getUniversities(): List<University> {
//        val cache = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        val jsonValue = cache.getString("universityList", null)

//        return gson.fromJson(jsonValue, Array<University>::class.java).toList()
        // handle null to avoid error
        return jsonValue?.let {
            return gson.fromJson(it, Array<University>::class.java).toList()
        } ?: listOf() // check if null, return empty list if null
    }

    fun setUniversities(universities: List<University>) { // setting cache
        val jsonValue = gson.toJson(universities)
//        val cache = context.getSharedPreferences(name, Context.MODE_PRIVATE).edit()
//        cache.putString("universityList", jsonValue)
//        cache.apply()

        cache.edit().apply {
            putString("universityList", jsonValue)
            apply()
        }
    }
}