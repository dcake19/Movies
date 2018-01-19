package com.example.android.movies.di.people.detailed

import com.example.android.movies.api.MoviesApi
import com.example.android.movies.di.ActivityScope
import com.example.android.movies.ui.people.detailed.credits.PeopleCreditsContract
import com.example.android.movies.ui.people.detailed.credits.PeopleCreditsInteractor
import com.example.android.movies.ui.people.detailed.credits.PeopleCreditsPresenter
import dagger.Module
import dagger.Provides

@Module
class PeopleDetailedModule {

    @Provides
    @ActivityScope
    fun providePeopleCreditsPresenter(interactor: PeopleCreditsInteractor): PeopleCreditsContract.Presenter{
        return PeopleCreditsPresenter(interactor)
    }

    @Provides
    @ActivityScope
    fun provideMovieCreditsInteractor(moviesApi: MoviesApi): PeopleCreditsInteractor {
        return PeopleCreditsInteractor(moviesApi)
    }

}