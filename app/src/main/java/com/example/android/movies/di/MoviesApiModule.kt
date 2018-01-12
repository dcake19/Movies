package com.example.android.movies.di

import com.example.android.movies.api.MoviesApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class MoviesApiModule {

    @Provides
    @Singleton
    fun provideMoviesApi(): MoviesApi{
        val retrofit = Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(MoviesApi::class.java)
    }
}