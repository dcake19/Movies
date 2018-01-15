package com.example.android.movies.ui.movies

interface MoviesContract {

    interface View{
        fun update(size:Int)
    }

    interface Presenter{
        fun downloadMoviesData()
        fun downloadMoviesDataNextPage()
        fun getPosterPath(index:Int):String
        fun getTitle(index: Int): String
    }

}