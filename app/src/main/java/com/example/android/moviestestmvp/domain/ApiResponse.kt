package com.example.android.moviestestmvp.domain

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @field:SerializedName("results") val results: List<Movie>,
    @field:SerializedName("page") val page: Int,
    @field:SerializedName("total_pages") val totalPages: Int
)