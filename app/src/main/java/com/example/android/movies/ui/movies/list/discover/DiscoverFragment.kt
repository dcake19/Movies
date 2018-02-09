package com.example.android.movies.ui.movies.list.discover

import android.os.Bundle
import android.view.View
import com.example.android.movies.ui.movies.list.BaseMoviesListFragment
import com.example.android.movies.ui.movies.list.search.MoviesSearchActivity


class DiscoverFragment : BaseMoviesListFragment(){

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addMultiPageRecyclerView()
    }
}