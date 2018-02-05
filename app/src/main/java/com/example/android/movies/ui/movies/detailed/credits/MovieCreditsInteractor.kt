package com.example.android.movies.ui.movies.detailed.credits

import com.example.android.movies.api.MoviesApi
import javax.inject.Inject


class MovieCreditsInteractor @Inject constructor(moviesApi: MoviesApi) : MoviesApi by moviesApi
