package com.example.android.moviestestmvp.infrastructure.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.moviestestmvp.domain.Movie

@Dao
interface MovieDao {
    @Query("SELECT * from movies WHERE category = :category")
    suspend fun getMoviesByCategory(category: String): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie)

    @Query("DELETE FROM movies")
    fun deleteAll()
}