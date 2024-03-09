package com.example.androidrecap.data.api

import com.example.androidrecap.models.University
import io.ktor.client.call.body
import io.ktor.client.request.get

class UniversityApi: BaseApi() {
    // need model here (list of <Model> Object)
    suspend fun getUniversities(): List<University> =
        client
            .get("http://universities.hipolabs.com/search?country=Thailand")
            .body<List<University>>() // explicitly define type of response (List of Objects)
}