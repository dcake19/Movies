package com.example.android.movies.di.people.list

import com.example.android.movies.RxSchedulerProvider
import com.example.android.movies.api.MoviesApi
import com.example.android.movies.di.ActivityScope
import com.example.android.movies.di.FragmentScope
import com.example.android.movies.ui.people.list.PeopleListAdapter
import com.example.android.movies.ui.people.list.PeopleListContract
import com.example.android.movies.ui.people.list.PeopleListInteractor
import com.example.android.movies.ui.people.list.PeopleListPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
abstract class PeopleListModule() {

    @Binds
    abstract fun bindPresenter(presenter: PeopleListPresenter):PeopleListContract.Presenter

}