package com.example.androidrecap.data.api
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.gson.gson

// install ktor
// val ktor_version = "2.3.4"
// in dependencies:
// implementation("io.ktor:ktor-client-android:$ktor_version")
// implementation("io.ktor:ktor-serialization-gson:$ktor_version")
// implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")



class BaseApi {
    val client = HttpClient(Android) {
        install(ContentNegotiation) {
            gson()
        }
    }
}