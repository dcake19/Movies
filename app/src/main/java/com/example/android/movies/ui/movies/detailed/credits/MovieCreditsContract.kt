package com.example.android.movies.ui.movies.detailed.credits


interface MovieCreditsContract {

    interface View{
        fun display(sizeCast:Int,sizeCrew:Int)
    }

    interface Presenter{
        fun changeView(view: View)
        fun downloadCredits(id:Int)
        fun getCastId(position: Int): Int
        fun getCastName(position:Int):String
        fun getCharacter(position:Int):String
        fun getCastPosterPath(position:Int):String
        fun getCrewId(position: Int): Int
        fun getCrewName(position:Int):String
        fun getJob(position:Int):String
        fun getCrewPosterPath(position:Int):String
    }
}