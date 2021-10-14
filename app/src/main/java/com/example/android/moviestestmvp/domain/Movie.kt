package com.example.android.moviestestmvp.domain

import com.google.gson.annotations.SerializedName

data class Movie(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("title") val title: String,
)