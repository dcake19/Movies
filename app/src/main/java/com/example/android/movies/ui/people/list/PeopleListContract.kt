package com.example.android.movies.ui.people.list

import android.content.Context


interface PeopleListContract {

    interface View{
        fun getContext(): Context
        fun display(size:Int)
    }

    interface Presenter{
        fun addView(view:View)
        fun downloadPopularPeople()
        fun searchPeople(query:String)
        fun downloadPeopleNextPage()
        fun getPosterPath(position:Int): String
        fun getPersonId(position:Int): Int
        fun getPersonName(position:Int): String
        fun getPersonKnownFor(position:Int): String
    }
}