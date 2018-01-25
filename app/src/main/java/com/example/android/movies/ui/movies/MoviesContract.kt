package com.example.android.movies.ui.movies

import android.content.Context

interface MoviesContract {

    interface View{
        fun getContext():Context
        fun update(size:Int)
    }

    interface Presenter{
        fun downloadMoviesData()
        fun downloadMoviesDataNextPage()
        fun downloadDiscoverData(discoverQuery: DiscoverQuery)
        fun search(query:String)
        fun getMovieId(index:Int): Int
        fun getPosterPath(index:Int):String
        fun getTitle(index: Int): String
        fun getYear(index: Int): String
        fun getVoteCount(index: Int): String
        fun getVoteAverage(index: Int): String
        fun getRatingBackgroundColor(index: Int): Int
        fun getRatingTextColor(index: Int): Int
    }

}