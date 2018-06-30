package com.example.android.movies.di

import com.example.android.movies.App
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        BuilderModule::class,
        AppModule::class,
        ContextModule::class))
interface AppComponent {
    fun inject(app: App)
}
