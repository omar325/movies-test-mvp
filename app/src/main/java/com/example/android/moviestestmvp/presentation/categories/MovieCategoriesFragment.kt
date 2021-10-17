package com.example.android.moviestestmvp.presentation.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.moviestestmvp.R
import com.example.android.moviestestmvp.domain.Movie
import com.example.android.moviestestmvp.domain.MovieCategory
import com.example.android.moviestestmvp.presentation.detail.MovieDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_categories.*
import javax.inject.Inject

@AndroidEntryPoint
class MovieCategoriesFragment: Fragment(), MovieCategoriesView {

    @Inject lateinit var interactor: MovieCategoriesInteractor

    private val presenter: MovieCategoriesPresenter by lazy {
        MovieCategoriesPresenter.Default(view = this, interactor, lifecycleScope)
    }

    private val movieListAdapter = MovieListAdapter() {
        goToMovieDetail(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(
        R.layout.fragment_movie_categories, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movie_list.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = movieListAdapter
        }

        movie_toggle.apply {
            addOnButtonCheckedListener { group, checkedId, isChecked ->
                if(isChecked) presenter.getMoviesByCategory(getCategoryFromView(checkedId), false)
            }

            check(R.id.latest_toggle)
        }.checkedButtonId.let { checkedId ->
            try_again_button.setOnClickListener {
                presenter.getMoviesByCategory(getCategoryFromView(checkedId), true)
            }
        }
    }

    private fun getCategoryFromView(viewId: Int): MovieCategory = when(viewId) {
        R.id.latest_toggle -> MovieCategory.LATEST
        R.id.popular_toggle -> MovieCategory.POPULAR
        else -> MovieCategory.UPCOMING
    }

    override fun showProgressBar(show: Boolean) {
        loading.visibility = if(show) VISIBLE else GONE
    }

    override fun updateList(movies: List<Movie>) {
        movieListAdapter.submitList(movies)
    }

    override fun showErrorMessage(show: Boolean) {
        error_layout.visibility = if(show) VISIBLE else GONE
    }

    override fun showList(show: Boolean) {
        movie_list.visibility = if(show) VISIBLE else GONE
    }

    private fun goToMovieDetail(movie: Movie) {
        activity?.supportFragmentManager?.beginTransaction()?.add(R.id.main_fragment_container, MovieDetailFragment(movie), MovieDetailFragment.TAG)?.addToBackStack(tag)?.commit()
    }
}