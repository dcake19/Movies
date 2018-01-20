package com.example.android.movies.ui.people.list


interface PeopleListContract {

    interface View{
        fun display(size:Int)
    }

    interface Presenter{
        fun downloadPopularPeople()
        fun searchPeople(query:String)
        fun getPosterPath(position:Int): String
        fun getPersonId(position:Int): Int
        fun getPersonName(position:Int): String
    }
}