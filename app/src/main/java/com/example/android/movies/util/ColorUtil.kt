package com.example.android.movies.util

import android.content.Context
import android.support.v4.content.ContextCompat
import com.example.android.movies.R


object ColorUtil {

    fun getRatingBackgroundColor(context: Context, rating: Double): Int {
        val color: Int
        if (rating <= 0)
            color = R.color.colorBlack
        else if (rating >= 8)
            color = R.color.colorUserScoreExcellent
        else if (rating >= 7)
            color = R.color.colorUserScoreVeryGood
        else if (rating >= 6)
            color = R.color.colorUserScoreGood
        else if (rating >= 5)
            color = R.color.colorUserScoreAverage
        else if (rating >= 4)
            color = R.color.colorUserScoreBad
        else if (rating >= 3)
            color = R.color.colorUserScoreVeryBad
        else
            color = R.color.colorUserScoreDreadful

        return ContextCompat.getColor(context, color)
    }

    fun getTextColor(context: Context, rating: Double): Int {
        val color: Int
        if (rating >= 3)
            color = R.color.colorBlack
        else
            color = R.color.colorWhite

        return ContextCompat.getColor(context, color)
    }

}