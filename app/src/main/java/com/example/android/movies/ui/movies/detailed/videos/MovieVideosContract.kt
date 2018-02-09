package com.example.android.movies.ui.movies.detailed.videos

import android.content.Context


interface MovieVideosContract {

    interface View{
        fun getContext():Context
        fun display(size:Int)
    }

    interface Presenter{
        fun addView(view: View)
        fun downloadVideoLinks(id:Int)
        fun getVideoTitle(position:Int):String
        fun launchVideo(position: Int)
    }
}