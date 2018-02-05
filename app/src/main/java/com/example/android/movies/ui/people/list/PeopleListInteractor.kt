package com.example.android.movies.ui.people.list

import com.example.android.movies.api.MoviesApi
import javax.inject.Inject


class PeopleListInteractor @Inject constructor(moviesApi: MoviesApi) : MoviesApi by moviesApi