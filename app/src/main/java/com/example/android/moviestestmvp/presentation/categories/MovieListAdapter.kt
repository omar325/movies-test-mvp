package com.example.android.moviestestmvp.presentation.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.android.moviestestmvp.R
import com.example.android.moviestestmvp.domain.Movie
import javax.inject.Inject

class MovieListAdapter(
    private val onMovieClickAction: (selectedMovie: Movie) -> Unit
): ListAdapter<Movie, MovieViewHolder>(CounterDiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder = MovieViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false),
        onMovieClickAction
    )

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int
    ) { holder.bind(getItem(position)) }
}

class MovieViewHolder(
    private val itemView: View,
    private val onMovieClickAction: (selectedMovie: Movie) -> Unit
): RecyclerView.ViewHolder(itemView) {

    fun bind(movie: Movie) {
        with(itemView.findViewById<ImageView>(R.id.movie_item)) {
            Glide.with(this)
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
                .into(this)

            setOnClickListener {
                onMovieClickAction(movie)
            }
        }
    }
}

object CounterDiffCallback: DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
       oldItem == newItem

}