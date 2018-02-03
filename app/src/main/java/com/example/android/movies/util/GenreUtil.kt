package com.example.android.movies.util

import android.content.Context
import com.example.android.movies.R


object GenreUtil {

    private fun getGenreId(index:Int):Int{
        when(index)
        {
            0 -> return 28
            1 -> return 12
            2 -> return 16
            3 -> return 35
            4 -> return 80
            5 -> return 99
            6 -> return 18
            7 -> return 10751
            8 -> return 14
            9 -> return 36
            10 -> return 27
            11 -> return 10402
            12 -> return 9648
            13 -> return 10749
            14 -> return 878
            15 -> return 10770
            16 -> return 53
            17 -> return 10752
            18 -> return 37
        }
        return -1
    }

    private fun getGenre(index: Int,context: Context):String{
        when(index)
        {
            0 -> return context.getString(R.string.genre_action)
            1 -> return context.getString(R.string.genre_adventure)
            2 -> return context.getString(R.string.genre_animation)
            3 -> return context.getString(R.string.genre_comedy)
            4 -> return context.getString(R.string.genre_crime)
            5 -> return context.getString(R.string.genre_documentary)
            6 -> return context.getString(R.string.genre_drama)
            7 -> return context.getString(R.string.genre_family)
            8 -> return context.getString(R.string.genre_fantasy)
            9 -> return context.getString(R.string.genre_history)
            10 -> return context.getString(R.string.genre_horror)
            11 -> return context.getString(R.string.genre_music)
            12 -> return context.getString(R.string.genre_mystery)
            13 -> return context.getString(R.string.genre_romance)
            14 -> return context.getString(R.string.genre_sci_fi)
            15 -> return context.getString(R.string.genre_tv_movie)
            16 -> return context.getString(R.string.genre_thriller)
            17 -> return context.getString(R.string.genre_war)
            18 -> return context.getString(R.string.genre_western)
        }
        return ""
    }

//    fun getGenresIdList(genres:BooleanArray):String{
//        val selected = StringBuilder()
//        var firstSelected = false
//        for(i in 0..18){
//            if (genres[i]){
//                if (firstSelected)
//                    selected.append(","+ getGenreId(i).toString())
//                else{
//                    firstSelected = true
//                    selected.append(getGenreId(i).toString())
//                }
//            }
//        }
//        return selected.toString()
//    }

    fun getGenresIdList(genres:BooleanArray):String{
        val selected = StringBuilder()
        val first = genres.indexOf(true)
        if (first>=0) {
            selected.append(getGenreId(first).toString())
            for (g in genres.mapIndexed({ i, b -> Pair(i, b) }).filter { it.second }.drop(1)) {
                selected.append("," + getGenreId(g.first).toString())
            }
        }
        return selected.toString()
    }

    fun getGenreList(genres:BooleanArray,context: Context):String{
        val selected = StringBuilder()
        val first = genres.indexOf(true)
        if (first>=0) {
            selected.append(getGenre(first,context).toString())
            for (g in genres.mapIndexed({ i, b -> Pair(i, b) }).filter { it.second }.drop(1)) {
                selected.append(", " + getGenre(g.first,context).toString())
            }
        }
        return selected.toString()

    }

//    fun getGenreList(genres:BooleanArray,context: Context):String{
//        val selected = StringBuilder()
//        var firstSelected = false
//        for(i in 0..18){
//            if (genres[i]){
//                if (firstSelected)
//                    selected.append(", "+ getGenre(i,context))
//                else{
//                    firstSelected = true
//                    selected.append(getGenre(i,context))
//                }
//            }
//        }
//        return selected.toString()
//    }

}

//{
//    "genres": [
//    {
//        "id": 28,
//        "name": "Action"
//    },
//    {
//        "id": 12,
//        "name": "Adventure"
//    },
//    {
//        "id": 16,
//        "name": "Animation"
//    },
//    {
//        "id": 35,
//        "name": "Comedy"
//    },
//    {
//        "id": 80,
//        "name": "Crime"
//    },
//    {
//        "id": 99,
//        "name": "Documentary"
//    },
//    {
//        "id": 18,
//        "name": "Drama"
//    },
//    {
//        "id": 10751,
//        "name": "Family"
//    },
//    {
//        "id": 14,
//        "name": "Fantasy"
//    },
//    {
//        "id": 36,
//        "name": "History"
//    },
//    {
//        "id": 27,
//        "name": "Horror"
//    },
//    {
//        "id": 10402,
//        "name": "Music"
//    },
//    {
//        "id": 9648,
//        "name": "Mystery"
//    },
//    {
//        "id": 10749,
//        "name": "Romance"
//    },
//    {
//        "id": 878,
//        "name": "Science Fiction"
//    },
//    {
//        "id": 10770,
//        "name": "TV Movie"
//    },
//    {
//        "id": 53,
//        "name": "Thriller"
//    },
//    {
//        "id": 10752,
//        "name": "War"
//    },
//    {
//        "id": 37,
//        "name": "Western"
//    }
//    ]
//}