package com.example.android.movies.ui.movies.list.search

import android.os.Bundle
import android.view.View
import com.example.android.movies.ui.movies.BaseMoviesListFragment


class SearchFragment : BaseMoviesListFragment(){

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        if (savedInstanceState!=null)
            presenter.getDownloadedMoviesData()
        else
            presenter.search(arguments.getString(
                    MoviesSearchActivity.SEARCH_QUERY,""))
        super.onViewCreated(view, savedInstanceState)
        addMultiPageRecyclerView()
    }

    override fun update(size: Int) {
        super.update(size)
        moveToTop()
    }


}