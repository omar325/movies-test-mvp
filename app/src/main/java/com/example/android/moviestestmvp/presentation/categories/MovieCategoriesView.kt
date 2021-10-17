package com.example.android.moviestestmvp.presentation.categories

import com.example.android.moviestestmvp.domain.Movie
import com.example.android.moviestestmvp.domain.MovieCategory

interface MovieCategoriesView {
    fun showProgressBar(show: Boolean)
    fun updateList(movies: List<Movie>)
    fun showErrorMessage(show: Boolean)
    fun showList(show: Boolean)
}