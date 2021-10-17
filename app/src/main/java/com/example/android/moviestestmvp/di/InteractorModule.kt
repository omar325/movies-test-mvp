package com.example.android.moviestestmvp.di

import com.example.android.moviestestmvp.presentation.categories.MovieCategoriesInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
abstract class MovieCategoriesInteractorModule {
    @Binds
    @FragmentScoped
    abstract fun bindMovieCategoriesInteractor(
        movieCategoriesInteractor: MovieCategoriesInteractor.Default
    ): MovieCategoriesInteractor
}