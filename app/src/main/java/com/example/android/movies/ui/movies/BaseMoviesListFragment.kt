package com.example.android.movies.ui.movies

import android.os.Bundle
import android.app.Fragment
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.movies.R
import com.example.android.movies.ui.EndlessRecyclerViewScrollListener
import com.example.android.movies.ui.movies.home.MoviesHomeActivity
import com.example.android.movies.ui.movies.list.MoviesListAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.movies_home_fragment.*
import timber.log.Timber
import javax.inject.Inject

abstract class BaseMoviesListFragment : Fragment(), MoviesContract.View {

    @Inject lateinit var presenter: MoviesContract.Presenter
    //@Inject
    lateinit var adapter: MoviesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        presenter.addView(this, arguments.getInt(MoviesHomeActivity.DOWNLOAD_TYPE_KEY))
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        adapter = MoviesListAdapter(presenter)
        return inflater!!.inflate(R.layout.movies_list_fragment,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_movies.apply {
            val linearLayout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            layoutManager = linearLayout
            isNestedScrollingEnabled = false
        }
        if (recycler_movies.adapter == null)
            recycler_movies.adapter = adapter
    }

    protected fun addMultiPageRecyclerView(){
        class EndlessListener(layout: LinearLayoutManager): EndlessRecyclerViewScrollListener(layout) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                //Log.v("EndlessListener","page: " + page.toString())
                presenter.downloadMoviesDataNextPage()
            }
        }
        recycler_movies.addOnScrollListener(
                EndlessListener(recycler_movies.layoutManager as LinearLayoutManager))
    }

    override fun update(size: Int) {
        adapter.update(size)
    }

    protected fun moveToTop(){
        recycler_movies.scrollToPosition(0)
    }

}