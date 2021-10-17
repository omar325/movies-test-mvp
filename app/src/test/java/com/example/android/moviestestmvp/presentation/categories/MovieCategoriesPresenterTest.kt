package com.example.android.moviestestmvp.presentation.categories

import com.example.android.moviestestmvp.domain.Movie
import com.example.android.moviestestmvp.domain.MovieCategory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class MovieCategoriesPresenterTest {
    @Mock
    lateinit var view: MovieCategoriesView

    @Mock
    lateinit var interactor: MovieCategoriesInteractor

    var presenter: MovieCategoriesPresenter? = null

    @Rule
    @JvmField val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        presenter = MovieCategoriesPresenter.Default(view, interactor, TestCoroutineScope())
    }

    @After
    fun tearDown() {
        presenter = null
    }

    @Test
    fun getMoviesByCategory_shows_progress_bar_and_hides_error_message() {
        presenter!!.getMoviesByCategory(MovieCategory.LATEST, false)
        then(view).should().showProgressBar(true)
        then(view).should().showErrorMessage(false)
    }

    @Test
    fun handleMovies_hides_progress_bar_and_error_message_and_shows_list_and_updates_list() {
        val movies = emptyList<Movie>()
        presenter!!.handleMovies(movies)
        then(view).should().showProgressBar(false)
        then(view).should().showErrorMessage(false)
        then(view).should().showList(true)
        then(view).should().updateList(movies)
    }

    @Test
    fun handleError_hides_progress_bar_and_shows_error_message() {
        presenter!!.handleError()
        then(view).should().showProgressBar(false)
        then(view).should().showErrorMessage(true)
        then(view).should().showList(false)
    }
}