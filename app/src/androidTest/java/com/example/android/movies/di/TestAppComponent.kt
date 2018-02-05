package com.example.android.movies.di

import com.example.android.movies.TestApp
import com.example.android.movies.ui.*
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
    fun inject(testPeopleListActivity: TestPeopleListActivity)
}