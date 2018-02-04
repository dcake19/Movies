package com.example.android.movies.di

import com.example.android.movies.TestApp
import com.example.android.movies.ui.TestMoviesDiscoverActivity
import com.example.android.movies.ui.TestMoviesHomeActivity
import com.example.android.movies.ui.TestMoviesListActivity
import com.example.android.movies.ui.TestMoviesSearchActivity
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        TestBuilderModule::class,
        TestAppModule::class))
interface TestAppComponent {
    fun inject(app: TestApp)
    fun inject(testMoviesListActivity: TestMoviesListActivity)
    fun inject(testMoviesHomeActivity: TestMoviesHomeActivity)
    fun inject(testMoviesSearchActivity: TestMoviesSearchActivity)
    fun inject(testMoviesDiscoverActivity: TestMoviesDiscoverActivity)
}