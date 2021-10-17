package com.example.android.moviestestmvp.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.android.moviestestmvp.R
import com.example.android.moviestestmvp.domain.Movie
import kotlinx.android.synthetic.main.fragment_movie_deatil.*

class MovieDetailFragment(
    private val movie: Movie
): Fragment() {

    companion object {
        val TAG = "MovieDetailFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_movie_deatil, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movie_title.text = movie.title
        release_date.text = movie.releaseDate
        rating_bar.rating = movie.voteAverage

        Glide.with(poster_thumbnail)
            .load(
                GlideUrl(
                    "https://image.tmdb.org/t/p/original/${movie.posterPath}",
                    LazyHeaders.Builder().addHeader(
                        "User-Agent",
                        "Mozilla/5.0 (Linux; Android 11) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.181 Mobile Safari/537.36"
                    ).build()
                )
            )
            .centerInside()
            .into(poster_thumbnail)

        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }
}