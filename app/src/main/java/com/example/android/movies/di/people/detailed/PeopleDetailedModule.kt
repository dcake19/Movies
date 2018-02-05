package com.example.android.movies.di.people.detailed

import com.example.android.movies.RxSchedulerProvider
import com.example.android.movies.api.MoviesApi
import com.example.android.movies.di.ActivityScope
import com.example.android.movies.di.FragmentScope
import com.example.android.movies.ui.people.detailed.credits.PeopleCreditsContract
import com.example.android.movies.ui.people.detailed.credits.PeopleCreditsFragment
import com.example.android.movies.ui.people.detailed.credits.PeopleCreditsInteractor
import com.example.android.movies.ui.people.detailed.credits.PeopleCreditsPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
abstract class PeopleDetailedModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun creditsFragment(): PeopleCreditsFragment

    @ActivityScope
    @Binds abstract fun bindPresenter(presenter: PeopleCreditsPresenter): PeopleCreditsContract.Presenter

//    @Provides
//    @ActivityScope
//    fun providePeopleCreditsPresenter(interactor: PeopleCreditsInteractor,
//                                      rxSchedulerProvider: RxSchedulerProvider)
//            : PeopleCreditsContract.Presenter{
//        return PeopleCreditsPresenter(interactor,rxSchedulerProvider)
//    }
//
//    @Provides
//    @ActivityScope
//    fun provideMovieCreditsInteractor(moviesApi: MoviesApi): PeopleCreditsInteractor {
//        return PeopleCreditsInteractor(moviesApi)
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