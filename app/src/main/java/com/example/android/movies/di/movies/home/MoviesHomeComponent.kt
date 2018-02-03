package com.example.android.movies.di.movies.home

import com.example.android.movies.di.AppComponent
import com.example.android.movies.di.FragmentScope
import com.example.android.movies.ui.movies.home.MoviesHomeFragment
import dagger.Component

//@FragmentScope
//@Component(modules = arrayOf(MoviesHomeModule::class),dependencies = arrayOf(AppComponent::class))
interface MoviesHomeComponent {
    fun inject(fragment: MoviesHomeFragment)
}
