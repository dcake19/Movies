package com.example.android.movies.ui.people.detailed.credits

import com.example.android.movies.api.MoviesApi
import javax.inject.Inject


class PeopleCreditsInteractor @Inject constructor(moviesApi: MoviesApi) : MoviesApi by moviesApi