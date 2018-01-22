package com.example.android.movies.util

import java.text.SimpleDateFormat

object DateUtil {

    fun getYearsArray(): ArrayList<String>{
        val dateFormat = SimpleDateFormat("y")
        val time = System.currentTimeMillis()
        var year:Int = dateFormat.format(time).toInt()

        val arrayYears = ArrayList<String>()
        arrayYears.add("")

        do{
            arrayYears.add(year.toString())
            year--
        }while (year>1950)

        return arrayYears
    }
}