package com.example.android.movies.ui.people.detailed.info


interface PeopleInfoContract {

    interface View{
        fun display(biography:String,posterPath:String)
    }

    interface Presenter{
        fun downloadPersonInfo(id:Int)
    }
}