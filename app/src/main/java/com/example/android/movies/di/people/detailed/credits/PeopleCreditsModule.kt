package com.example.android.movies.di.people.detailed.credits

import com.example.android.movies.di.FragmentScope
import com.example.android.movies.ui.people.detailed.credits.PeopleCreditsAdapter
import com.example.android.movies.ui.people.detailed.credits.PeopleCreditsContract
import dagger.Module
import dagger.Provides

//@Module
class PeopleCreditsModule (val view: PeopleCreditsContract.View, val creditsType:Int) {

//    @Provides
//    @FragmentScope
//    fun provideMovieCreditAdapter(presenter: PeopleCreditsContract.Presenter): PeopleCreditsAdapter {
//        addView(presenter)
//        return PeopleCreditsAdapter(presenter,creditsType)
//    }
//
//    private fun addView(presenter: PeopleCreditsContract.Presenter){
//        presenter.changeView(view)
//    }

}