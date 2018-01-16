package com.example.android.movies.di.movies.detailed.credits

import com.example.android.movies.di.AppComponent
import com.example.android.movies.di.FragmentScope
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsFragment
import dagger.Component


@FragmentScope
@Component(modules = arrayOf(MovieCreditsModule::class),dependencies = arrayOf(AppComponent::class))
interface MovieCreditsComponent {
    fun inject(fragment: MovieCreditsFragment)
}
