package com.example.android.movies.util

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
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

    fun getLightColor(palette: Palette):Int{
        val psLightMuted = palette.lightMutedSwatch?:Palette.Swatch(Color.YELLOW,1)
        val psLightVibrant = palette.lightVibrantSwatch?:Palette.Swatch(Color.YELLOW,1)
        val colorLight:Int
        if(psLightVibrant.population > 1)
            colorLight = psLightVibrant.rgb
        else
            colorLight = psLightMuted.rgb
        return colorLight
    }

    fun getDarkColor(palette: Palette):Int{
        val psDarkMuted = palette.darkMutedSwatch?:Palette.Swatch(Color.BLACK,1)
        val psDarkVibrant = palette.darkVibrantSwatch?:Palette.Swatch(Color.BLACK,1)
        val colorDark:Int
        if(psDarkMuted.population >= psDarkVibrant.population)
            colorDark = psDarkMuted.rgb
        else
            colorDark = psDarkVibrant.rgb
        return colorDark
    }

    fun getLighterShade(color:Int):Int{
        val hsv = FloatArray(3)
       // var hsv = floatArrayOf()
        var retColor = color
        Color.colorToHSV(color, hsv)
        hsv[2] *= 1.35f
        retColor = Color.HSVToColor(hsv)
        return retColor
    }

}