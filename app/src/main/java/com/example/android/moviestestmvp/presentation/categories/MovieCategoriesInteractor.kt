package com.example.android.moviestestmvp.presentation.categories

import com.example.android.moviestestmvp.domain.Movie
import com.example.android.moviestestmvp.domain.MovieCategory
import com.example.android.moviestestmvp.infrastructure.Repository
import com.example.android.moviestestmvp.infrastructure.retrofit.RetrofitRepository
import com.example.android.moviestestmvp.infrastructure.room.RoomRepository
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
interface MovieCategoriesInteractor {

    suspend fun getMoviesByCategory(categoryPath: String, forceOnline: Boolean): List<Movie>

    class Default @Inject constructor(
        private val remoteRepository: RetrofitRepository,
        private val localRepository: RoomRepository,
    ): MovieCategoriesInteractor {

        override suspend fun getMoviesByCategory(
            categoryPath: String,
            forceOnline: Boolean
        ): List<Movie> {
            if(forceOnline) {
                return fetchOnlineByCategory(categoryPath)
            }
            val movies = localRepository.getMoviesByCategory(categoryPath).results
            if(movies.isNotEmpty()) {
                return movies
            }
            return fetchOnlineByCategory(categoryPath)
        }

        private suspend fun fetchOnlineByCategory(categoryPath: String): List<Movie> {
            val movies = remoteRepository.getMoviesByCategory(categoryPath).results
            for (movie in movies) {
                movie.category = categoryPath
                localRepository.saveMovie(movie)
            }
            return movies
        }
    }
}