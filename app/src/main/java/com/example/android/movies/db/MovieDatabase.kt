package com.example.android.movies.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by Daniel on 30/06/2018.
 */

@Database(entities = arrayOf(FavoriteMovies::class), version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
}