package com.example.android.movies.di

import com.example.android.movies.RxSchedulerProvider
import com.example.android.movies.api.MoviesApi
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import javax.inject.Singleton

@Module
class TestAppModule {

    @Singleton
    @Provides
    fun provideMoviesApi(): MoviesApi {
        return Mockito.mock(MoviesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideScheduler(): RxSchedulerProvider {
        return Mockito.mock(RxSchedulerProvider::class.java)
    }

}
