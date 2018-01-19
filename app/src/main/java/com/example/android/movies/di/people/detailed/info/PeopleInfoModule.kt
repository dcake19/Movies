package com.example.android.movies.di.people.detailed.info

import com.example.android.movies.api.MoviesApi
import com.example.android.movies.di.FragmentScope
import com.example.android.movies.ui.movies.detailed.info.MoviesInfoInteractor
import com.example.android.movies.ui.people.detailed.info.PeopleInfoContract
import com.example.android.movies.ui.people.detailed.info.PeopleInfoInteractor
import com.example.android.movies.ui.people.detailed.info.PeopleInfoPresenter
import dagger.Module
import dagger.Provides

@Module
class PeopleInfoModule(val view: PeopleInfoContract.View) {

    @Provides
    @FragmentScope
    fun providePeopleInfoPresenter(interactor: PeopleInfoInteractor): PeopleInfoContract.Presenter{
        return PeopleInfoPresenter(interactor, view)
    }

    @Provides
    @FragmentScope
    fun providePeopleInfoInteractor(moviesApi: MoviesApi): PeopleInfoInteractor{
        return PeopleInfoInteractor(moviesApi)
    }
}