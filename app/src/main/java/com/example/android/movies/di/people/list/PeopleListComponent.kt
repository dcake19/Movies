package com.example.android.movies.di.people.list

import com.example.android.movies.di.AppComponent
import com.example.android.movies.di.FragmentScope
import com.example.android.movies.ui.people.list.PeopleListFragment
import dagger.Component

@FragmentScope
@Component(modules = arrayOf(PeopleListModule::class),dependencies = arrayOf(AppComponent::class))
interface PeopleListComponent {
    fun inject(fragment: PeopleListFragment)
}