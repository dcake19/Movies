package com.example.android.movies.di.movies.detailed

import com.example.android.movies.api.MoviesApi
import com.example.android.movies.di.ActivityScope
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsContract
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsInteractor
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsPresenter
import dagger.Module
import dagger.Provides


@Module class MovieDetailedModule {

    @Provides
    @ActivityScope
    fun provideMovieCreditsPresenter(interactor: MovieCreditsInteractor): MovieCreditsContract.Presenter{
        return MovieCreditsPresenter(interactor)
    }

    @Provides
    @ActivityScope
    fun provideMovieCreditsInteractor(moviesApi: MoviesApi): MovieCreditsInteractor {
        return MovieCreditsInteractor(moviesApi)
    }

}