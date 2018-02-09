package com.example.android.movies.di.movies.list

import com.example.android.movies.di.FragmentScope
import com.example.android.movies.ui.movies.MoviesContract
import com.example.android.movies.ui.movies.MoviesDiscoverPresenter
import com.example.android.movies.ui.movies.list.BaseMoviesListFragment
import com.example.android.movies.ui.movies.list.discover.DiscoverFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MoviesDiscoverModule {

//    @ContributesAndroidInjector
//    @FragmentScope
//    abstract fun moviesListFragment(): BaseMoviesListFragment

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun moviesListFragment(): DiscoverFragment

    @Binds
    abstract fun bindPresenter(presenter: MoviesDiscoverPresenter): MoviesContract.Presenter
}