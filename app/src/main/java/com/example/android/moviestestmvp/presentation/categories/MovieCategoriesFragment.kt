package com.example.android.moviestestmvp.presentation.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.android.moviestestmvp.R
import com.example.android.moviestestmvp.domain.MovieCategory
import com.example.android.moviestestmvp.infrastructure.retrofit.RetrofitRepository
import kotlinx.android.synthetic.main.fragment_movie_categories.*
import kotlinx.coroutines.launch

class MovieCategoriesFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(
        R.layout.fragment_movie_categories, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            RetrofitRepository.create().getMoviesByCategory(MovieCategory.LATEST.path).let {
                textView.text = it.results.toString()
            }
        }
    }
}