package com.example.android.movies.util

import android.util.Log


object TextUtil {

    fun<T> convertToCommaSeparatedString(start:String,list: List<T>?,itemString:(T)->String):String{
        if (list!=null) {
            val sb = StringBuilder()
            if (list.isNotEmpty()) sb.append(start + itemString(list[0]))
            for (item in list.drop(1))
                if(itemString(item)!="") sb.append(", " + itemString(item))
            return sb.toString()
        } else{
            return ""
        }
    }

    fun getYearFromDate(date:String):String{
        if(date.length>3)
            return date.substring(0..3)
        else return ""
    }

    fun convertMoney(type:String,dollars:Int):String{
        return convertMoney(type,dollars.toLong())
    }

    fun convertMoney(type:String,dollars:Long):String{
        val value:String
        if(dollars<1000) value = dollars.toString()
        else if(dollars<1000000) value =  Math.round(dollars.toDouble()/1000).toString() + "k"
        else if(dollars<1000000000) value =  Math.round(dollars.toDouble()/1000000).toString() + "m"
        else  value = Math.round(dollars.toDouble()/1000000000).toString() + "bn"
        return type + "$" + value
    }

}