package com.example.android.movies.di.movies.detailed

import com.example.android.movies.di.ActivityScope
import com.example.android.movies.di.FragmentScope
import com.example.android.movies.ui.movies.MoviesContract
import com.example.android.movies.ui.movies.detailed.related.MoviesRelatedFragment
import com.example.android.movies.ui.movies.detailed.related.MoviesRelatedMoviesPresenter
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsContract
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsFragment
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsPresenter
import com.example.android.movies.ui.movies.detailed.videos.MovieDetailsVideosFragment
import com.example.android.movies.ui.movies.detailed.videos.MovieVideosContract
import com.example.android.movies.ui.movies.detailed.videos.MovieVideosPresenter
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MovieDetailedModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun creditsFragment():MovieCreditsFragment

//    @ContributesAndroidInjector
//    @FragmentScope
//    abstract fun moviesListFragment(): BaseMoviesListFragment

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun moviesListFragment(): MoviesRelatedFragment

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun videosFragment(): MovieDetailsVideosFragment

    @ActivityScope
    @Binds abstract fun bindCreditsPresenter(presenter: MovieCreditsPresenter): MovieCreditsContract.Presenter


    @Binds
    abstract fun bindPresenter(presenter: MoviesRelatedMoviesPresenter): MoviesContract.Presenter

    @Binds
    abstract fun bindVideosPresenter(presenter: MovieVideosPresenter): MovieVideosContract.Presenter

}