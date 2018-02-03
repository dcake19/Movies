package com.example.android.movies.di.movies.list

import com.example.android.movies.RxSchedulerProvider
import com.example.android.movies.api.MoviesApi
import com.example.android.movies.di.FragmentScope
import com.example.android.movies.ui.movies.MoviesContract
import com.example.android.movies.ui.movies.MoviesInteractor
import com.example.android.movies.ui.movies.MoviesPresenter
import com.example.android.movies.ui.movies.home.MoviesHomeAdapter
import com.example.android.movies.ui.movies.list.MoviesListAdapter
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


//@Module
//class MoviesListModule(val view: MoviesContract.View, val type:Int) {
//@Module
abstract class MoviesListModule{

   // @Binds
   // abstract fun bindPresenter(presenter:MoviesPresenter)

//    @Provides
//    @FragmentScope
//    fun provideMoviesPresenter(interactor: MoviesInteractor,rxSchedulerProvider: RxSchedulerProvider): MoviesContract.Presenter{
//        return MoviesPresenter(interactor,rxSchedulerProvider, view, type)
//    }
//
//    @Provides
//    @FragmentScope
//    fun provideMoviesInteractor(moviesApi: MoviesApi): MoviesInteractor {
//        return MoviesInteractor(moviesApi)
//    }
//
//    @Provides
//    @FragmentScope
//    fun provideRxSchedulerProvider():RxSchedulerProvider{
//        return object : RxSchedulerProvider{
//            override fun subscribeOn(): Scheduler {
//                return Schedulers.io()
//            }
//            override fun observeOn(): Scheduler {
//                return AndroidSchedulers.mainThread()
//            }
//        }
//    }
//
//    @Provides
//    @FragmentScope
//    fun provideMoviesAdapter(presenter: MoviesContract.Presenter): MoviesListAdapter {
//        return MoviesListAdapter(presenter)
//    }


}