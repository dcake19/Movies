package com.example.android.movies.di.movies.detailed.info

import com.example.android.movies.RxSchedulerProvider
import com.example.android.movies.api.MoviesApi
import com.example.android.movies.di.FragmentScope
import com.example.android.movies.ui.movies.detailed.info.MoviesInfoContract
import com.example.android.movies.ui.movies.detailed.info.MoviesInfoInteractor
import com.example.android.movies.ui.movies.detailed.info.MoviesInfoPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
abstract class MoviesInfoModule{//val view: MoviesInfoContract.View){

    @Binds
    abstract fun bindPresenter(moviesInfoPresenter: MoviesInfoPresenter):MoviesInfoContract.Presenter

//    @Provides
//    @FragmentScope
//    fun provideMoviesPresenter(interactor: MoviesInfoInteractor,
//                               rxSchedulerProvider: RxSchedulerProvider)
//            : MoviesInfoContract.Presenter{
//        return MoviesInfoPresenter(interactor,rxSchedulerProvider,view)
//    }
//
//    @Provides
//    @FragmentScope
//    fun provideMoviesInteractor(moviesApi: MoviesApi): MoviesInfoInteractor {
//        return MoviesInfoInteractor(moviesApi)
//    }
//
//    @Provides
//    @FragmentScope
//    fun provideRxSchedulerProvider(): RxSchedulerProvider {
//        return object : RxSchedulerProvider {
//            override fun subscribeOn(): Scheduler {
//                return Schedulers.io()
//            }
//            override fun observeOn(): Scheduler {
//                return AndroidSchedulers.mainThread()
//            }
//        }
//    }

}
