package com.example.android.movies.ui.movies

import com.example.android.movies.RxSchedulerProvider
import javax.inject.Inject


class MoviesDiscoverPresenter @Inject constructor(
        interactor: MoviesInteractor, rxSchedulerProvider: RxSchedulerProvider)
    : MoviesPresenter(interactor,rxSchedulerProvider) {

    private var discoverQuery = DiscoverQuery()

    override fun downloadMoviesDataNextPage() {
         if (moviesResults.page < moviesResults.totalPages)
             downloadDiscoverData(discoverQuery,moviesResults.page+1)
    }

    override fun downloadDiscoverData(discoverQuery: DiscoverQuery, page: Int) {
        if(page==1) this.discoverQuery = discoverQuery
        super.downloadDiscoverData(discoverQuery, page)
    }
}