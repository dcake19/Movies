package com.example.android.movies.di

import com.example.android.movies.di.movies.detailed.MovieDetailedModule
import com.example.android.movies.di.movies.detailed.MovieRelatedMoviesModule
import com.example.android.movies.di.movies.detailed.info.MoviesInfoModule
import com.example.android.movies.di.movies.home.MoviesHomeModule
import com.example.android.movies.di.movies.home.MoviesModule
import com.example.android.movies.di.movies.list.MoviesDiscoverModule
import com.example.android.movies.di.movies.list.MoviesListModule
import com.example.android.movies.di.movies.list.MoviesSearchModule
import com.example.android.movies.di.people.detailed.PeopleDetailedModule
import com.example.android.movies.di.people.detailed.info.PeopleInfoModule
import com.example.android.movies.di.people.list.PeopleListModule
import com.example.android.movies.ui.movies.detailed.MovieDetailsActivity
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsFragment
import com.example.android.movies.ui.movies.detailed.info.MoviesInfoFragment
import com.example.android.movies.ui.movies.home.MoviesHomeActivity
import com.example.android.movies.ui.movies.home.MoviesHomeFragment
import com.example.android.movies.ui.movies.list.MoviesListActivity
import com.example.android.movies.ui.movies.list.MoviesListFragment
import com.example.android.movies.ui.movies.list.discover.MoviesDiscoverActivity
import com.example.android.movies.ui.movies.list.search.MoviesSearchActivity
import com.example.android.movies.ui.people.detailed.PeopleDetailedActivity
import com.example.android.movies.ui.people.detailed.info.PeopleInfoFragment
import com.example.android.movies.ui.people.list.PeopleListActivity
import com.example.android.movies.ui.people.list.PeopleListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuilderModule {

//    @ContributesAndroidInjector(modules = arrayOf(MoviesModule::class))
//    @FragmentScope
//    abstract fun moviesHomeFragment(): MoviesHomeFragment

    @ContributesAndroidInjector(modules = arrayOf(MoviesHomeModule::class))
    abstract fun moviesHomeActivity(): MoviesHomeActivity



//    @ContributesAndroidInjector(modules = arrayOf(MoviesModule::class))
//    @FragmentScope
//    abstract fun moviesListFragment(): MoviesListFragment

    @ContributesAndroidInjector(modules = arrayOf(MoviesListModule::class))
    abstract fun moviesListActivity(): MoviesListActivity



    @ContributesAndroidInjector(modules = arrayOf(MoviesDiscoverModule::class))
    abstract fun moviesDiscoverActivity(): MoviesDiscoverActivity

    @ContributesAndroidInjector(modules = arrayOf(MoviesSearchModule::class))
    abstract fun moviesSearchActivity(): MoviesSearchActivity

//    @ContributesAndroidInjector(modules = arrayOf(MovieRelatedMoviesModule::class))
//    @FragmentScope
//    abstract fun moviesListFragment(): MoviesListFragment

    @ContributesAndroidInjector(modules = arrayOf(MoviesInfoModule::class))
    abstract fun moviesInfoFragment(): MoviesInfoFragment



    @ContributesAndroidInjector(modules = arrayOf(MovieDetailedModule::class))
    @ActivityScope
    abstract fun movieDetailActivity(): MovieDetailsActivity



    @ContributesAndroidInjector(modules = arrayOf(PeopleInfoModule::class))
    abstract fun peopleInfoFragment(): PeopleInfoFragment


    @ContributesAndroidInjector(modules = arrayOf(PeopleDetailedModule::class))
    @ActivityScope
    abstract fun peopleDetailActivity(): PeopleDetailedActivity



    @ContributesAndroidInjector(modules = arrayOf(PeopleListModule::class))
    @FragmentScope
    abstract fun peopleListFragment(): PeopleListFragment

    @ContributesAndroidInjector
    abstract fun peopleListActivity(): PeopleListActivity

}