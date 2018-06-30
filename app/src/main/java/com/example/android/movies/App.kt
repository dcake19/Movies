package com.example.android.movies

import android.app.Activity
import android.app.Application
import android.content.Context
import com.example.android.movies.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject


//class App : Application(){
//
//    lateinit var component: AppComponent
//
//    override fun onCreate() {
//        super.onCreate()
//        component  = DaggerAppComponent.builder().build()
//    }
//
//}

open class App : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    //  @Inject
    //   lateinit var model:Model

    override fun onCreate() {
        super.onCreate()
        Holder.INSTANCE = this
        Timber.plant(Timber.DebugTree())

        createComponent()
    }

    open fun createComponent(){
        DaggerAppComponent.create()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    object Holder{lateinit var INSTANCE : App}

    companion object {
        val instance: App by lazy { Holder.INSTANCE }
        fun appContext() = instance.applicationContext
    }

}