package com.example.android.movies.di.people.detailed.info

import com.example.android.movies.di.AppComponent
import com.example.android.movies.di.FragmentScope
import com.example.android.movies.ui.people.detailed.info.PeopleInfoContract
import com.example.android.movies.ui.people.detailed.info.PeopleInfoFragment
import dagger.Component

@FragmentScope
@Component(modules = arrayOf(PeopleInfoModule::class),dependencies = arrayOf(AppComponent::class))
interface PeopleInfoComponent {
    fun inject(fragment: PeopleInfoFragment)
}