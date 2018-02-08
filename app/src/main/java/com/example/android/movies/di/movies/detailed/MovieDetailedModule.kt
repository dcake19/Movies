package com.example.android.movies.di.movies.detailed

import com.example.android.movies.RxSchedulerProvider
import com.example.android.movies.api.MoviesApi
import com.example.android.movies.di.ActivityScope
import com.example.android.movies.di.FragmentScope
import com.example.android.movies.ui.movies.MoviesContract
import com.example.android.movies.ui.movies.detailed.MoviesRelatedMoviesPresenter
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsContract
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsFragment
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsInteractor
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsPresenter
import com.example.android.movies.ui.movies.list.MoviesListFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
abstract class MovieDetailedModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun creditsFragment():MovieCreditsFragment

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun moviesListFragment(): MoviesListFragment

    @ActivityScope
    @Binds abstract fun bindCreditsPresenter(presenter: MovieCreditsPresenter): MovieCreditsContract.Presenter


    @Binds
    abstract fun bindPresenter(presenter: MoviesRelatedMoviesPresenter): MoviesContract.Presenter

}