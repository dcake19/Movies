package com.example.android.movies.ui.movies

import com.example.android.movies.api.MoviesApi


class MoviesInteractor(moviesApi: MoviesApi) : MoviesApi by moviesApi
