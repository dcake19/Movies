package com.example.android.movies.di.movies.list

import com.example.android.movies.di.FragmentScope
import com.example.android.movies.ui.movies.MoviesContract
import com.example.android.movies.ui.movies.MoviesDiscoverPresenter
import com.example.android.movies.ui.movies.MoviesPresenter
import com.example.android.movies.ui.movies.list.MoviesListFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MoviesDiscoverModule {

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun moviesListFragment(): MoviesListFragment

    @Binds
    abstract fun bindPresenter(presenter: MoviesDiscoverPresenter): MoviesContract.Presenter
}