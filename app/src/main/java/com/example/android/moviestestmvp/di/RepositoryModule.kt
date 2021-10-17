package com.example.android.moviestestmvp.di

import android.content.Context
import androidx.room.Room
import com.example.android.moviestestmvp.infrastructure.retrofit.RetrofitRepository
import com.example.android.moviestestmvp.infrastructure.room.RoomRepository
import com.example.android.moviestestmvp.infrastructure.room.MovieDao
import com.example.android.moviestestmvp.infrastructure.room.MovieRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideRemoteRepository(): RetrofitRepository =
        RetrofitRepository.create()

    @Provides
    @Singleton
    fun provideLocalRepository(movieDao: MovieDao): RoomRepository =
        RoomRepository(movieDao)

    @Provides
    fun provideMovieDao(database: MovieRoomDatabase): MovieDao =
        database.movieDao()

    @Provides
    @Singleton
    fun provideMovieDatabase(
        @ApplicationContext appContext: Context
    ): MovieRoomDatabase = Room.databaseBuilder(
        appContext,
        MovieRoomDatabase::class.java,
        "movies.db"
    ).build()
}