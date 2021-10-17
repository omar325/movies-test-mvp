package com.example.android.moviestestmvp.presentation.categories

import androidx.lifecycle.LifecycleCoroutineScope
import com.example.android.moviestestmvp.domain.Movie
import com.example.android.moviestestmvp.domain.MovieCategory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface MovieCategoriesPresenter {

    fun getMoviesByCategory(category: MovieCategory, forceOnline: Boolean)
    fun handleMovies(movies: List<Movie>)
    fun handleError()

    class Default(
        private val view: MovieCategoriesView,
        private val interactor: MovieCategoriesInteractor,
        private val lifecycleScope: CoroutineScope
    ): MovieCategoriesPresenter {

        override fun  getMoviesByCategory(
            category: MovieCategory,
            forceOnline: Boolean
        ) {
            view.showProgressBar(true)
            view.showErrorMessage(false)

            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    val movies = interactor.getMoviesByCategory(category.path, forceOnline)
                    lifecycleScope.launch(Dispatchers.Main) { handleMovies(movies) }
                } catch (e: Exception) {
                    e.printStackTrace()
                    lifecycleScope.launch(Dispatchers.Main) { handleError() }
                }
            }
        }

        override fun handleMovies(movies: List<Movie>) {
            view.showProgressBar(false)
            view.showErrorMessage(false)
            view.showList(true)
            view.updateList(movies)
        }

        override fun handleError() {
            view.showProgressBar(false)
            view.showErrorMessage(true)
            view.showList(false)
        }
    }
}