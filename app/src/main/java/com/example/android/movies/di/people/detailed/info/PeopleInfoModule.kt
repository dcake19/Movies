package com.example.android.movies.di.people.detailed.info

import com.example.android.movies.RxSchedulerProvider
import com.example.android.movies.api.MoviesApi
import com.example.android.movies.di.FragmentScope
import com.example.android.movies.ui.movies.detailed.info.MoviesInfoInteractor
import com.example.android.movies.ui.people.detailed.info.PeopleInfoContract
import com.example.android.movies.ui.people.detailed.info.PeopleInfoInteractor
import com.example.android.movies.ui.people.detailed.info.PeopleInfoPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

//@Module
class PeopleInfoModule(val view: PeopleInfoContract.View) {

//    @Provides
//    @FragmentScope
//    fun providePeopleInfoPresenter(interactor: PeopleInfoInteractor,
//                                   rxSchedulerProvider: RxSchedulerProvider)
//            : PeopleInfoContract.Presenter{
//        return PeopleInfoPresenter(interactor,rxSchedulerProvider,view)
//    }
//
//    @Provides
//    @FragmentScope
//    fun providePeopleInfoInteractor(moviesApi: MoviesApi): PeopleInfoInteractor{
//        return PeopleInfoInteractor(moviesApi)
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