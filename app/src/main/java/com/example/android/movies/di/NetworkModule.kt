package com.example.android.movies.di

import com.squareup.picasso.OkHttpDownloader
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor{
        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger{
            override fun log(message: String?) {
                Timber.i(message)
            }
        })
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        return interceptor
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient{
        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
    }

//    @Singleton
//    @Provides
//    fun provideOkHttp3Downloader(okHttpClient: OkHttpClient): OkHttp3Downloader{
//        return OkHttp3Downloader(okHttpClient)
//    }

}