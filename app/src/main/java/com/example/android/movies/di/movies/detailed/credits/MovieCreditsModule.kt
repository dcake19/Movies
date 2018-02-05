package com.example.android.movies.di.movies.detailed.credits

import com.example.android.movies.api.MoviesApi
import com.example.android.movies.di.ActivityScope
import com.example.android.movies.di.FragmentScope
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsAdapter
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsContract
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsInteractor
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsPresenter
import dagger.Binds

import dagger.Module
import dagger.Provides

//@Module
//abstract class MovieCreditsModule(val view: MovieCreditsContract.View,val creditsType:Int) {
abstract class MovieCreditsModule{

   // @Binds
   // abstract fun bindPresenter(presenter: MovieCreditsPresenter): MovieCreditsContract.Presenter
    //bind


//    @Provides
//    @FragmentScope
//    fun provideMovieCreditAdapter(presenter: MovieCreditsContract.Presenter): MovieCreditsAdapter {
//        addView(presenter)
//        return MovieCreditsAdapter(presenter,creditsType)
//    }
//
//    private fun addView(presenter: MovieCreditsContract.Presenter){
//        presenter.changeView(view)
//    }

}