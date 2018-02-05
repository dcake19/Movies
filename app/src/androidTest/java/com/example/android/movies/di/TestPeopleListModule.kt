package com.example.android.movies.di

import com.example.android.movies.ui.movies.MoviesContract
import com.example.android.movies.ui.people.list.PeopleListContract
import dagger.Module
import dagger.Provides
import org.mockito.Mockito

@Module
class TestPeopleListModule {

    @Provides
    fun providePresenter():PeopleListContract.Presenter{
        val presenter = Mockito.mock(PeopleListContract.Presenter::class.java)
        for (i in 0..39) {
            Mockito.`when`(presenter.getPersonId(i)).thenReturn(i)
            Mockito.`when`(presenter.getPersonName(i)).thenReturn("Person " + i)
            Mockito.`when`(presenter.getPersonKnownFor(i)).thenReturn("Known for: Film 1, Film 2, Film 3")
            Mockito.`when`(presenter.getPosterPath(i)).thenReturn("")
        }

        return presenter
    }
}