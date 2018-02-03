package com.example.android.movies.di

import com.example.android.movies.App
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

//@Singleton
//@Component(modules = arrayOf(MoviesApiModule::class))
//interface AppComponent {
//    fun getMoviesApi(): MoviesApi
//}

@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        BuilderModule::class,
        AppModule::class))
interface AppComponent {
    fun inject(app: App)
}
