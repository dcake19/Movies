package com.example.android.movies.di.movies.detailed

import com.example.android.movies.di.ActivityScope
import com.example.android.movies.di.AppComponent
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsContract
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsPresenter

import dagger.Component

//@ActivityScope
//@Component(modules = arrayOf(MovieDetailedModule::class),dependencies = arrayOf(AppComponent::class))
interface MovieDetailedComponent {
    fun getMovieCreditsPresenter(): MovieCreditsContract.Presenter
}