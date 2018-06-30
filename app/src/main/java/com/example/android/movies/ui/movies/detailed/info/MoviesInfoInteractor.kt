package com.example.android.movies.ui.movies.detailed.info

import com.example.android.movies.api.MoviesApi
import com.example.android.movies.db.FavoritesDao
import javax.inject.Inject


class MoviesInfoInteractor @Inject constructor(moviesApi: MoviesApi,favoritesDao: FavoritesDao)
    : MoviesApi by moviesApi,FavoritesDao by favoritesDao
