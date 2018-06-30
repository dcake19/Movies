package com.example.android.movies.ui.movies.detailed.info

import android.content.Context


interface MoviesInfoContract {

    interface View{
        fun getContext():Context
        fun display(overview:String,posterPath:String,
                    year:String,status:String,userScore:String,
                    voteCount:String,budget:String,revenue:String,
                    runtime:String,genres:String,languages:String,
                    ratingBackgroundColor:Int,ratingTextColor:Int)
        fun setFavorite()
    }

    interface Presenter{
        fun addView(view:View)
        fun downloadMovieInfo(id:Int)
        fun setFavorite(id:Int)
        fun changeFavorite()
        fun isFavorite():Boolean
    }
}