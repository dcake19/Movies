package com.example.android.movies.di.movies.detailed.info

import com.example.android.movies.di.AppComponent
import com.example.android.movies.di.FragmentScope
import com.example.android.movies.ui.movies.detailed.info.MoviesInfoFragment
import dagger.Component

//@FragmentScope
//@Component(modules = arrayOf(MoviesInfoModule::class),dependencies = arrayOf(AppComponent::class))
interface MoviesInfoComponent {
    fun inject(fragment: MoviesInfoFragment)
}