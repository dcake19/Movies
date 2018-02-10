package com.example.android.movies.ui.movies

import android.content.Context
import com.example.android.movies.ui.movies.list.discover.DiscoverQuery

interface MoviesContract {

    interface View{
        fun getContext():Context
        fun update(size:Int)
    }

    interface Presenter{
        fun addView(view:View,type:Int)
        fun getDownloadedMoviesData()
        fun downloadMoviesData(page:Int=1)
        fun downloadMoviesDataNextPage()
        fun downloadDiscoverData(discoverQuery: DiscoverQuery, page:Int=1)
        fun search(query:String,page:Int=1)
        fun downloadRelatedMovies(id:Int,page:Int=1)
        fun getMovieId(index:Int): Int
        fun getPosterPath(index:Int):String
        fun getBackdropPath(index:Int):String
        fun getTitle(index: Int): String
        fun getYear(index: Int): String
        fun getVoteCount(index: Int): String
        fun getVoteAverage(index: Int): String
        fun getRatingBackgroundColor(index: Int): Int
        fun getRatingTextColor(index: Int): Int
    }

}