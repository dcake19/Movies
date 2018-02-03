package com.example.android.movies.ui.movies

import com.example.android.movies.api.MoviesApi
import javax.inject.Inject


class MoviesInteractor @Inject constructor(moviesApi: MoviesApi) : MoviesApi by moviesApi
