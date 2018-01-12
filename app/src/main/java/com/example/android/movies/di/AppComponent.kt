package com.example.android.movies.di

import com.example.android.movies.api.MoviesApi
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(MoviesApiModule::class))
interface AppComponent {
    fun getMoviesApi(): MoviesApi
}