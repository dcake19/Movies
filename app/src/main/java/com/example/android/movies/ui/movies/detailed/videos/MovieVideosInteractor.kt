package com.example.android.movies.ui.movies.detailed.videos

import com.example.android.movies.api.MoviesApi
import javax.inject.Inject


class MovieVideosInteractor  @Inject constructor(moviesApi: MoviesApi)
    : MoviesApi by moviesApi
