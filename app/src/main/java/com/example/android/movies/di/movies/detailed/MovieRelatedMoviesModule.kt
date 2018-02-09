package com.example.android.movies.di.movies.detailed

import com.example.android.movies.ui.movies.MoviesContract
import com.example.android.movies.ui.movies.detailed.related.MoviesRelatedMoviesPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class MovieRelatedMoviesModule {

//    @ContributesAndroidInjector
//    @FragmentScope
//    abstract fun moviesListFragment(): BaseMoviesListFragment

    @Binds
    abstract fun bindPresenter(presenter: MoviesRelatedMoviesPresenter): MoviesContract.Presenter

}