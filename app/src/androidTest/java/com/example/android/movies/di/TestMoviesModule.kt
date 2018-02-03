package com.example.android.movies.di

import com.example.android.movies.ui.movies.MoviesContract
import dagger.Module
import dagger.Provides
import org.mockito.Mockito

@Module
class TestMoviesModule {

    @Provides
    fun providePresenter():MoviesContract.Presenter{
        val presenter = Mockito.mock(MoviesContract.Presenter::class.java)

        for(i in 0..39) {
            Mockito.`when`(presenter.getMovieId(i)).thenReturn(i)
            Mockito.`when`(presenter.getTitle(i)).thenReturn("Title " + i)
            Mockito.`when`(presenter.getPosterPath(i)).thenReturn("")
            var year = "20"
            if(i<10) year += "0"
            year += i.toString()
            Mockito.`when`(presenter.getYear(i)).thenReturn(year)
            Mockito.`when`(presenter.getVoteCount(i))
                    .thenReturn(i.toString() + " votes")
            val va = year[2] + "." + year[3]
            Mockito.`when`(presenter.getVoteAverage(i)).thenReturn(va)

        }

        return presenter
    }

}