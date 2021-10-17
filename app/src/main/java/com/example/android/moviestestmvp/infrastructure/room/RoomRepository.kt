package com.example.android.moviestestmvp.infrastructure.room

import com.example.android.moviestestmvp.domain.ApiResponse
import com.example.android.moviestestmvp.domain.Movie
import com.example.android.moviestestmvp.infrastructure.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomRepository @Inject constructor(
    private val movieDao: MovieDao
): Repository{

    override suspend fun getMoviesByCategory(
        category: String
    ): ApiResponse = ApiResponse(
        results = movieDao.getMoviesByCategory(category), 0, 0
    )

    fun saveMovie(movie: Movie) {
        movieDao.insert(movie)
    }
}