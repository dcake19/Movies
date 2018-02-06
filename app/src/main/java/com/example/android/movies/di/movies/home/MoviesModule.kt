package com.example.android.movies.di.movies.home

import com.example.android.movies.RxSchedulerProvider
import com.example.android.movies.api.MoviesApi
import com.example.android.movies.di.FragmentScope
import com.example.android.movies.ui.movies.home.MoviesHomeAdapter
import com.example.android.movies.ui.movies.MoviesContract
import com.example.android.movies.ui.movies.MoviesInteractor
import com.example.android.movies.ui.movies.MoviesPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
abstract class MoviesModule {

    @Binds
    abstract fun bindPresenter(presenter:MoviesPresenter):MoviesContract.Presenter

}