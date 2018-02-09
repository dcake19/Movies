package com.example.android.movies.ui.movies.list

import com.example.android.movies.RxSchedulerProvider
import com.example.android.movies.ui.movies.MoviesInteractor
import com.example.android.movies.ui.movies.MoviesPresenter
import javax.inject.Inject


class MoviesListPresenter @Inject constructor(
        interactor: MoviesInteractor, rxSchedulerProvider: RxSchedulerProvider)
    : MoviesPresenter(interactor,rxSchedulerProvider) {

    override fun downloadMoviesDataNextPage() {
         if (moviesResults.page < moviesResults.totalPages)
            downloadMoviesData(moviesResults.page+1)
    }

}