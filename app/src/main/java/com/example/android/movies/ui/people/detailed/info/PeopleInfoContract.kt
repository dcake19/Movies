package com.example.android.movies.ui.people.detailed.info

import android.content.Context


interface PeopleInfoContract {

    interface View{
        fun getContext():Context
        fun display(biography:String,posterPath:String,born:String)
    }

    interface Presenter{
        fun downloadPersonInfo(id:Int)
    }
}