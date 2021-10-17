package com.example.android.moviestestmvp.infrastructure.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android.moviestestmvp.domain.Movie

@Database(entities = arrayOf(Movie::class), version = 1)
abstract class MovieRoomDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}