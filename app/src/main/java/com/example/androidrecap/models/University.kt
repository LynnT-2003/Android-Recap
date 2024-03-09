package com.example.androidrecap.models

data class University(
    val name: String,
    val alpha_two_code: String,
    val `state-province`: String,
    val domains: List<String>,
    val web_pages: List<String>
)

//{
//    "alpha_two_code": "TH",
//    "web_pages": [
//    "https://www.ait.ac.th/"
//    ],
//    "state-province": "Pathum Thani",
//    "name": "Asian Institute of Technology",
//    "domains": [
//    "ait.ac.th"
//    ],
//    "country": "Thailand"
//},
