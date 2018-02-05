package com.example.android.movies.ui.movies.detailed.info

import com.example.android.movies.api.MoviesApi
import javax.inject.Inject


class MoviesInfoInteractor @Inject constructor(moviesApi: MoviesApi) : MoviesApi by moviesApi
