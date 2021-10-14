package com.example.android.moviestestmvp.domain

enum class MovieCategory(val title: String, val path: String) {
    LATEST("Latest", "now_playing"),
    POPULAR("Popular", "popular"),
    UPCOMING("Upcoming", "upcoming")
}