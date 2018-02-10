package com.example.android.movies.ui.movies.list

import android.os.Bundle
import android.view.View
import com.example.android.movies.ui.movies.BaseMoviesListFragment


class MoviesListFragment : BaseMoviesListFragment(){

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        presenter.downloadMoviesData()
        super.onViewCreated(view, savedInstanceState)
        addMultiPageRecyclerView()
    }
}