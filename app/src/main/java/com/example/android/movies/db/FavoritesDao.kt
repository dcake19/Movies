package com.example.android.movies.db

import android.arch.persistence.room.*

/**
 * Created by Daniel on 30/06/2018.
 */
@Dao
interface FavoritesDao{

    @Query("SELECT * FROM " + FavoritesTableNames.FAVORITES)
    fun getAll() : MutableList<FavoriteMovies>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favoriteMovies: FavoriteMovies)

    @Delete
    fun deleteMovie(favoriteMovies: FavoriteMovies)
}