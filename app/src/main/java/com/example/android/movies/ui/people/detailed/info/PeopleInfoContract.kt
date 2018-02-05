package com.example.android.movies.ui.people.detailed.info

import android.content.Context
import android.view.View


interface PeopleInfoContract {

    interface View{
        fun getContext():Context
        fun display(biography:String,posterPath:String,born:String)
    }

    interface Presenter{
        fun addView(view: View)
        fun downloadPersonInfo(id:Int)
    }
}