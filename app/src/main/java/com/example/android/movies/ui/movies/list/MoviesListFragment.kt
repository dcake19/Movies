package com.example.android.movies.ui.movies.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.example.android.movies.ui.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.movies_home_fragment.*


class MoviesListFragment : BaseMoviesListFragment(){

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        presenter.downloadMoviesData()
        super.onViewCreated(view, savedInstanceState)
        addMultiPageRecyclerView()
//        class EndlessListener(layout: LinearLayoutManager): EndlessRecyclerViewScrollListener(layout) {
//            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
//                Log.v("EndlessListener","page: " + page.toString())
//                presenter.downloadMoviesDataNextPage()
//            }
//        }
//
//        recycler_movies.addOnScrollListener(
//                EndlessListener(recycler_movies.layoutManager as LinearLayoutManager))
    }
}