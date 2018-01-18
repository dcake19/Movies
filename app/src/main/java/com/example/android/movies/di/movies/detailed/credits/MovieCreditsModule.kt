package com.example.android.movies.di.movies.detailed.credits

import com.example.android.movies.api.MoviesApi
import com.example.android.movies.di.ActivityScope
import com.example.android.movies.di.FragmentScope
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsAdapter
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsContract
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsInteractor
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsPresenter

import dagger.Module
import dagger.Provides

@Module class MovieCreditsModule(val creditsType:Int) {
//@Module class MovieCreditsModule(val view: MovieCreditsContract.View,val creditsType:Int) {

//    @Provides
//    @FragmentScope
//    fun provideMovieCreditsPresenter(interactor: MovieCreditsInteractor): MovieCreditsContract.Presenter{
//        return MovieCreditsPresenter(interactor)
//    }
//    @Provides
//    @FragmentScope
//    fun provideMovieCreditsPresenter(interactor: MovieCreditsInteractor): MovieCreditsContract.Presenter{
//        return MovieCreditsPresenter(interactor,view)
//    }
//
//    @Provides
//    @FragmentScope
//    fun provideMovieCreditsInteractor(moviesApi: MoviesApi): MovieCreditsInteractor {
//        return MovieCreditsInteractor(moviesApi)
//    }

//    @Provides
//    @FragmentScope
//    fun provideMovieCreditsPresenter(presenter: MovieCreditsContract.Presenter):
//            MovieCreditsContract.Presenter{
//        return presenter
//    }


    @Provides
    @FragmentScope
    fun provideMovieCreditAdapter(presenter: MovieCreditsContract.Presenter): MovieCreditsAdapter {
        return MovieCreditsAdapter(presenter,creditsType)
    }

}