package com.example.android.movies.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(val context:Context) {

    @Singleton
    @Provides
    fun provideContext(): Context {
        return context
    }
}