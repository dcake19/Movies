package com.example.android.movies.di.movies.list

import com.example.android.movies.di.FragmentScope
import com.example.android.movies.ui.movies.MoviesContract
import com.example.android.movies.ui.movies.list.MoviesListPresenter
import com.example.android.movies.ui.movies.list.MoviesListFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MoviesListModule {

//    @ContributesAndroidInjector
//    @FragmentScope
//    abstract fun moviesListFragment(): BaseMoviesListFragment

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun moviesListFragment(): MoviesListFragment

    @Binds
    abstract fun bindPresenter(presenter: MoviesListPresenter): MoviesContract.Presenter

}