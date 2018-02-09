package com.example.android.movies.ui.movies.detailed.related

import com.example.android.movies.RxSchedulerProvider
import com.example.android.movies.ui.movies.MoviesInteractor
import com.example.android.movies.ui.movies.MoviesPresenter
import javax.inject.Inject


class MoviesRelatedMoviesPresenter @Inject constructor(
        interactor: MoviesInteractor, rxSchedulerProvider: RxSchedulerProvider)
    : MoviesPresenter(interactor,rxSchedulerProvider) {

    private var id :Int =0

    override fun downloadMoviesDataNextPage() {
        if (moviesResults.page < moviesResults.totalPages)
            downloadRelatedMovies(id,moviesResults.page + 1)
    }

    override fun downloadRelatedMovies(id: Int, page: Int) {
        if (page==1) this.id = id
        super.downloadRelatedMovies(id, page)
    }
}