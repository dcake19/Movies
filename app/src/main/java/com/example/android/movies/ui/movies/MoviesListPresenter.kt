package com.example.android.movies.ui.movies

import com.example.android.movies.RxSchedulerProvider
import javax.inject.Inject


class MoviesListPresenter @Inject constructor(
        interactor: MoviesInteractor, rxSchedulerProvider: RxSchedulerProvider)
    : MoviesPresenter(interactor,rxSchedulerProvider) {

    override fun downloadMoviesDataNextPage() {
         if (moviesResults.page < moviesResults.totalPages)
            downloadMoviesData(moviesResults.page+1)
    }

}