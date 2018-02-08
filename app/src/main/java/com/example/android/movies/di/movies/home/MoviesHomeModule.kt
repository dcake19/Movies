package com.example.android.movies.di.movies.home

import com.example.android.movies.di.FragmentScope
import com.example.android.movies.ui.movies.MoviesContract
import com.example.android.movies.ui.movies.MoviesListPresenter
import com.example.android.movies.ui.movies.MoviesPresenter
import com.example.android.movies.ui.movies.home.MoviesHomeFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MoviesHomeModule {

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun moviesHomeFragment(): MoviesHomeFragment

    @Binds
    abstract fun bindPresenter(presenter: MoviesListPresenter): MoviesContract.Presenter
}