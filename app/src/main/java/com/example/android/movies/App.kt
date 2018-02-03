package com.example.android.movies

import android.app.Activity
import android.app.Application
import com.example.android.movies.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
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
        createComponent()
    }

    open fun createComponent(){
        DaggerAppComponent.create()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

}