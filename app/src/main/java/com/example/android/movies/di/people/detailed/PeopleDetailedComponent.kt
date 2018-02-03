package com.example.android.movies.di.people.detailed

import com.example.android.movies.di.ActivityScope
import com.example.android.movies.di.AppComponent
import com.example.android.movies.ui.people.detailed.credits.PeopleCreditsContract
import dagger.Component

//@ActivityScope
//@Component(modules = arrayOf(PeopleDetailedModule::class),dependencies = arrayOf(AppComponent::class))
interface PeopleDetailedComponent {
    fun getPeopleCreditsPresenter():PeopleCreditsContract.Presenter
}