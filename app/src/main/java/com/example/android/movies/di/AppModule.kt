package com.example.android.movies.di

import android.arch.persistence.room.Room
import com.example.android.movies.App
import com.example.android.movies.RxSchedulerProvider
import com.example.android.movies.api.MoviesApi
import com.example.android.movies.db.FavoriteMovies
import com.example.android.movies.db.FavoritesDao
import com.example.android.movies.db.MovieDatabase
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.OkHttpClient

@Module(includes = arrayOf(NetworkModule::class))
class AppModule {

    @Singleton
    @Provides
    fun provideMoviesApi(okHttpClient:OkHttpClient): MoviesApi {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(MoviesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRxSchedulerProvider(): RxSchedulerProvider {
        return object : RxSchedulerProvider {
            override fun subscribeOn(): Scheduler {
                return Schedulers.io()
            }
            override fun observeOn(): Scheduler {
                return AndroidSchedulers.mainThread()
            }
        }
    }

    @Singleton
    @Provides
    fun provideFavoritesDao() : FavoritesDao{
        val database = Room.databaseBuilder(App.appContext(),MovieDatabase::class.java,"database").build()
        return database.favoritesDao()
    }

}