package com.example.android.movies.ui.movies.list.discover

import android.os.Bundle
import android.view.View
import com.example.android.movies.ui.movies.BaseMoviesListFragment


class DiscoverFragment : BaseMoviesListFragment(){

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        presenter.getDownloadedMoviesData()
        super.onViewCreated(view, savedInstanceState)
        addMultiPageRecyclerView()
    }

    override fun update(size: Int) {
        super.update(size)
        moveToTop()
    }
}