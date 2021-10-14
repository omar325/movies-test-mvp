package com.example.android.moviestestmvp.infrastructure

import com.example.android.moviestestmvp.domain.ApiResponse
import com.example.android.moviestestmvp.domain.Movie

interface Repository {
    suspend fun getMoviesByCategory(category: String): ApiResponse
}