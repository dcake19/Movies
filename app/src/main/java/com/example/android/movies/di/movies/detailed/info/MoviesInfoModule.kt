package com.example.android.movies.di.movies.detailed.info

import com.example.android.movies.api.MoviesApi
import com.example.android.movies.di.FragmentScope
import com.example.android.movies.ui.movies.detailed.info.MoviesInfoContract
import com.example.android.movies.ui.movies.detailed.info.MoviesInfoInteractor
import com.example.android.movies.ui.movies.detailed.info.MoviesInfoPresenter
import dagger.Module
import dagger.Provides

@Module class MoviesInfoModule(val view: MoviesInfoContract.View){

    @Provides
    @FragmentScope
    fun provideMoviesPresenter(interactor: MoviesInfoInteractor): MoviesInfoContract.Presenter{
        return MoviesInfoPresenter(interactor, view)
    }

    @Provides
    @FragmentScope
    fun provideMoviesInteractor(moviesApi: MoviesApi): MoviesInfoInteractor {
        return MoviesInfoInteractor(moviesApi)
    }

}
