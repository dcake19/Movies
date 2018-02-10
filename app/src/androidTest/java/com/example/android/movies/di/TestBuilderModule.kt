package com.example.android.movies.di

import com.example.android.movies.ui.movies.home.MoviesHomeActivity
import com.example.android.movies.ui.movies.home.MoviesHomeFragment
import com.example.android.movies.ui.movies.list.MoviesListActivity
import com.example.android.movies.ui.movies.BaseMoviesListFragment
import com.example.android.movies.ui.movies.list.discover.MoviesDiscoverActivity
import com.example.android.movies.ui.movies.list.search.MoviesSearchActivity
import com.example.android.movies.ui.people.list.PeopleListActivity
import com.example.android.movies.ui.people.list.PeopleListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TestBuilderModule {

    @ContributesAndroidInjector(modules = arrayOf(TestMoviesModule::class))
    @FragmentScope
    abstract fun moviesHomeFragment(): MoviesHomeFragment

    @ContributesAndroidInjector
    abstract fun moviesHomeActivity(): MoviesHomeActivity

    @ContributesAndroidInjector(modules = arrayOf(TestMoviesModule::class))
    @FragmentScope
    abstract fun moviesListFragment(): BaseMoviesListFragment

    @ContributesAndroidInjector
    abstract fun moviesListActivity(): MoviesListActivity

    @ContributesAndroidInjector
    abstract fun moviesDiscoverActivity(): MoviesDiscoverActivity

    @ContributesAndroidInjector
    abstract fun moviesSearchActivity(): MoviesSearchActivity

    @ContributesAndroidInjector(modules = arrayOf(TestPeopleListModule::class))
    @FragmentScope
    abstract fun peopleListFragment(): PeopleListFragment

    @ContributesAndroidInjector
    abstract fun peopleListActivity(): PeopleListActivity
}