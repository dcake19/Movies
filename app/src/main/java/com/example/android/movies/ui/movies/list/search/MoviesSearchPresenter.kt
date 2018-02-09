package com.example.android.movies.ui.movies.list.search

import com.example.android.movies.RxSchedulerProvider
import com.example.android.movies.ui.movies.MoviesInteractor
import com.example.android.movies.ui.movies.MoviesPresenter
import javax.inject.Inject


class MoviesSearchPresenter@Inject constructor(
        interactor: MoviesInteractor, rxSchedulerProvider: RxSchedulerProvider)
    : MoviesPresenter(interactor,rxSchedulerProvider) {

    private var searchQuery = ""

    override fun downloadMoviesDataNextPage() {
        if (moviesResults.page < moviesResults.totalPages)
            search(searchQuery,moviesResults.page+1)
    }

    override fun search(query: String, page: Int) {
        if (page==1) searchQuery = query
        super.search(query, page)
    }
}