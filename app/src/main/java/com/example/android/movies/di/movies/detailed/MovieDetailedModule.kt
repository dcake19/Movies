package com.example.android.movies.di.movies.detailed

import com.example.android.movies.RxSchedulerProvider
import com.example.android.movies.api.MoviesApi
import com.example.android.movies.di.ActivityScope
import com.example.android.movies.di.FragmentScope
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsContract
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsFragment
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsInteractor
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsPresenter
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

    @ActivityScope
    @Binds abstract fun bindPresenter(presenter: MovieCreditsPresenter): MovieCreditsContract.Presenter


//    @Provides
//    @ActivityScope
//    fun provideMovieCreditsPresenter(interactor: MovieCreditsInteractor,
//                                     rxSchedulerProvider: RxSchedulerProvider)
//            : MovieCreditsContract.Presenter{
//        return MovieCreditsPresenter(interactor,rxSchedulerProvider)
//    }
//
//    @Provides
//    @ActivityScope
//    fun provideMovieCreditsInteractor(moviesApi: MoviesApi): MovieCreditsInteractor {
//        return MovieCreditsInteractor(moviesApi)
//    }
//
//    @Provides
//    @ActivityScope
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