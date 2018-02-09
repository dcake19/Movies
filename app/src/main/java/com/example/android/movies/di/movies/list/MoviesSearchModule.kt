package com.example.android.movies.di.movies.list

import com.example.android.movies.di.FragmentScope
import com.example.android.movies.ui.movies.MoviesContract
import com.example.android.movies.ui.movies.MoviesSearchPresenter
import com.example.android.movies.ui.movies.list.BaseMoviesListFragment
import com.example.android.movies.ui.movies.list.search.SearchFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MoviesSearchModule {

//    @ContributesAndroidInjector
//    @FragmentScope
//    abstract fun moviesListFragment(): BaseMoviesListFragment

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun moviesListFragment(): SearchFragment

    @Binds
    abstract fun bindPresenter(presenter: MoviesSearchPresenter): MoviesContract.Presenter
}