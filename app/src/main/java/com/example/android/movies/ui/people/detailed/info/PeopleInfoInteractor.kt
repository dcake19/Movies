package com.example.android.movies.ui.people.detailed.info

import com.example.android.movies.api.MoviesApi
import javax.inject.Inject


class PeopleInfoInteractor @Inject constructor(moviesApi: MoviesApi) : MoviesApi by moviesApi