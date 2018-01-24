package com.example.android.movies.ui.people.detailed.credits

import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsContract

interface PeopleCreditsContract{

    interface View{
        fun display(sizeCast:Int,sizeCrew:Int)
    }

    interface Presenter{
        fun changeView(view: PeopleCreditsContract.View)
        fun downloadCredits(id:Int)
        fun getCastMovieId(position:Int):Int
        fun getCastMovieName(position:Int):String
        fun getCharacter(position:Int):String
        fun getCastMoviePosterPath(position:Int):String
        fun getCrewMovieId(position:Int):Int
        fun getCrewMovieName(position:Int):String
        fun getJob(position:Int):String
        fun getCrewMoviePosterPath(position:Int):String
    }
}
