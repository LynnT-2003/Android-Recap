package com.example.androidrecap.data.api

import io.ktor.client.call.body
import io.ktor.client.request.get

class UniversityApi: BaseApi() {
    // need model here (list of <Model> Object)
    suspend fun getUniversities(): List<String> =
        client
            .get("http://universities.hipolabs.com/search?country=Thailand")
            .body<List<String>>() // explicitly define type of response (List of Objects)
}