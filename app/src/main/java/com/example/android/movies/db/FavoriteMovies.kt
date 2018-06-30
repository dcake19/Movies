package com.example.android.movies.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Daniel on 30/06/2018.
 */
@Entity(tableName = FavoritesTableNames.FAVORITES)
class FavoriteMovies {
    @PrimaryKey
    var tmdbId = -1

  //  @ColumnInfo(name = FavoritesColumns.MOVIE_TITLE)
    //var title = ""

}