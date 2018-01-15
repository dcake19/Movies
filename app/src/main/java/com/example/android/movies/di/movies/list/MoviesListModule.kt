package com.example.android.movies.di.movies.list

import com.example.android.movies.api.MoviesApi
import com.example.android.movies.di.FragmentScope
import com.example.android.movies.ui.movies.MoviesContract
import com.example.android.movies.ui.movies.MoviesInteractor
import com.example.android.movies.ui.movies.MoviesPresenter
import com.example.android.movies.ui.movies.home.MoviesHomeAdapter
import com.example.android.movies.ui.movies.list.MoviesListAdapter
import dagger.Module
import dagger.Provides


@Module
class MoviesListModule(val view: MoviesContract.View, val type:Int) {

    @Provides
    @FragmentScope
    fun provideMoviesPresenter(interactor: MoviesInteractor): MoviesContract.Presenter{
        return MoviesPresenter(interactor, view, type)
    }

    @Provides
    @FragmentScope
    fun provideMoviesInteractor(moviesApi: MoviesApi): MoviesInteractor {
        return MoviesInteractor(moviesApi)
    }

    @Provides
    @FragmentScope
    fun provideMoviesAdapter(presenter: MoviesContract.Presenter): MoviesListAdapter {
        return MoviesListAdapter(presenter)
    }


}