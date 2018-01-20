package com.example.android.movies.di.people.list

import com.example.android.movies.api.MoviesApi
import com.example.android.movies.di.FragmentScope
import com.example.android.movies.ui.people.list.PeopleListAdapter
import com.example.android.movies.ui.people.list.PeopleListContract
import com.example.android.movies.ui.people.list.PeopleListInteractor
import com.example.android.movies.ui.people.list.PeopleListPresenter
import dagger.Module
import dagger.Provides

@Module
class PeopleListModule(val view: PeopleListContract.View) {

    @Provides
    @FragmentScope
    fun provideMoviesPresenter(interactor: PeopleListInteractor):PeopleListContract.Presenter{
        return PeopleListPresenter(interactor,view)
    }

    @Provides
    @FragmentScope
    fun provideMoviesInteractor(moviesApi: MoviesApi): PeopleListInteractor {
        return PeopleListInteractor(moviesApi)
    }

    @Provides
    @FragmentScope
    fun provideMoviesAdapter(presenter: PeopleListContract.Presenter): PeopleListAdapter {
        return PeopleListAdapter(presenter)
    }


}