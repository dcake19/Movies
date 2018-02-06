package com.example.android.movies.di.people.detailed.info

import com.example.android.movies.RxSchedulerProvider
import com.example.android.movies.api.MoviesApi
import com.example.android.movies.di.FragmentScope
import com.example.android.movies.ui.movies.detailed.info.MoviesInfoInteractor
import com.example.android.movies.ui.people.detailed.info.PeopleInfoContract
import com.example.android.movies.ui.people.detailed.info.PeopleInfoInteractor
import com.example.android.movies.ui.people.detailed.info.PeopleInfoPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
abstract class PeopleInfoModule(val view: PeopleInfoContract.View) {

    @Binds
    abstract fun bindPresenter(moviesInfoPresenter: PeopleInfoPresenter):PeopleInfoContract.Presenter

}