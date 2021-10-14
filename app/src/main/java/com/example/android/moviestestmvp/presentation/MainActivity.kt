package com.example.android.moviestestmvp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.moviestestmvp.R
import com.example.android.moviestestmvp.presentation.categories.MovieCategoriesFragment
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_container, MovieCategoriesFragment())
            .commit()
    }
}