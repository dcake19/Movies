package com.example.android.movies.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun getMoviesApi():MoviesApi{
    val retrofit = Retrofit.Builder()
            .baseUrl("http://api.themoviedb.org")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    return retrofit.create(MoviesApi::class.java)
}