package com.example.android.movies.di.movies.list

import com.example.android.movies.di.AppComponent
import com.example.android.movies.di.FragmentScope
import com.example.android.movies.di.movies.home.MoviesHomeModule
import com.example.android.movies.ui.movies.home.MoviesHomeFragment
import com.example.android.movies.ui.movies.list.MoviesListFragment
import dagger.Component


@FragmentScope
@Component(modules = arrayOf(MoviesListModule::class),dependencies = arrayOf(AppComponent::class))
interface MoviesListComponent {
    fun inject(fragment: MoviesListFragment)
}
