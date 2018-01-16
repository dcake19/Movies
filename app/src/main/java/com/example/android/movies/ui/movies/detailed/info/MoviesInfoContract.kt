package com.example.android.movies.ui.movies.detailed.info


interface MoviesInfoContract {

    interface View{
        fun display(title:String,overview:String,posterPath:String)
    }

    interface Presenter{
        fun downloadMovieInfo(id:Int)
    }
}