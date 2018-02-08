package com.example.android.movies.di.movies.detailed

import com.example.android.movies.di.FragmentScope
import com.example.android.movies.ui.movies.MoviesContract
import com.example.android.movies.ui.movies.detailed.MoviesRelatedMoviesPresenter
import com.example.android.movies.ui.movies.list.MoviesListFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MovieRelatedMoviesModule {

//    @ContributesAndroidInjector
//    @FragmentScope
//    abstract fun moviesListFragment(): MoviesListFragment

    @Binds
    abstract fun bindPresenter(presenter: MoviesRelatedMoviesPresenter): MoviesContract.Presenter

}