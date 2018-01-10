package com.example.android.movies.ui.movies

import com.example.android.movies.api.data.movie.MovieResults
import io.reactivex.Observable


interface MoviesContract {

    interface View{
        fun update(size:Int)
    }

    interface Presenter{
        fun downloadMoviesData()
        fun getPosterPath(index:Int):String
    }

}