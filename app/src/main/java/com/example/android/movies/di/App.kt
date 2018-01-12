package com.example.android.movies.di

import android.app.Application


class App : Application(){

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component  = DaggerAppComponent.builder().build()
    }

}