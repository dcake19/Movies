package com.example.android.movies.di.people.detailed.credits

import com.example.android.movies.di.FragmentScope
import com.example.android.movies.di.people.detailed.PeopleDetailedComponent
import com.example.android.movies.ui.people.detailed.credits.PeopleCreditsFragment
import dagger.Component

//@FragmentScope
//@Component(modules = arrayOf(PeopleCreditsModule::class),dependencies = arrayOf(PeopleDetailedComponent::class))
interface PeopleCreditsComponent {
    fun inject(fragment: PeopleCreditsFragment)
}