package com.example.android.movies.di

import com.example.android.movies.di.movies.home.MoviesModule
import com.example.android.movies.ui.movies.home.MoviesHomeActivity
import com.example.android.movies.ui.movies.home.MoviesHomeFragment
import com.example.android.movies.ui.movies.list.MoviesListActivity
import com.example.android.movies.ui.movies.list.MoviesListFragment
import com.example.android.movies.ui.movies.list.discover.MoviesDiscoverActivity
import com.example.android.movies.ui.movies.list.search.MoviesSearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TestBuilderModule {

//    @ContributesAndroidInjector(modules = arrayOf(TestMoviesModule::class))
//    @FragmentScope
//    abstract fun moviesHomeFragment(): MoviesHomeFragment
//
//    @ContributesAndroidInjector
//    abstract fun moviesHomeActivity(): MoviesHomeActivity

    @ContributesAndroidInjector(modules = arrayOf(TestMoviesModule::class))
    @FragmentScope
    abstract fun moviesListFragment(): MoviesListFragment

    @ContributesAndroidInjector
    abstract fun moviesListActivity(): MoviesListActivity

//    @ContributesAndroidInjector
//    abstract fun moviesDiscoverActivity(): MoviesDiscoverActivity
//
//    @ContributesAndroidInjector
//    abstract fun moviesSearchActivity(): MoviesSearchActivity
}