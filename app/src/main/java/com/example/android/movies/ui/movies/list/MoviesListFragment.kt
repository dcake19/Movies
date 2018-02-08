package com.example.android.movies.ui.movies.list

import android.os.Bundle
import android.app.Fragment
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.movies.R
import com.example.android.movies.ui.EndlessRecyclerViewScrollListener
import com.example.android.movies.ui.movies.MoviesContract
import com.example.android.movies.ui.movies.MoviesDownloadTypes
import com.example.android.movies.ui.movies.detailed.MovieDetailsActivity
import com.example.android.movies.ui.movies.home.MoviesHomeActivity
import com.example.android.movies.ui.movies.list.search.MoviesSearchActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.movies_home_fragment.*
import javax.inject.Inject

class MoviesListFragment : Fragment(), MoviesContract.View {

    @Inject lateinit var presenter: MoviesContract.Presenter
    //@Inject
    lateinit var adapter: MoviesListAdapter

    override fun onAttach(context: Context?) {
        AndroidInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter.addView(this, arguments.getInt(MoviesHomeActivity.DOWNLOAD_TYPE_KEY))
        adapter = MoviesListAdapter(presenter)
        return inflater!!.inflate(R.layout.movies_list_fragment,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        val type = arguments.getInt(MoviesListActivity.DOWNLOAD_TYPE_KEY)

        if (type == MoviesDownloadTypes.SEARCH)
            presenter.search(arguments.getString(MoviesSearchActivity.SEARCH_QUERY,""))
        else if(type == MoviesDownloadTypes.RECOMMENDATIONS || type == MoviesDownloadTypes.SIMILAR)
            presenter.downloadRelatedMovies(arguments.getInt(MovieDetailsActivity.MOVIE_ID))
        else if(type != MoviesDownloadTypes.DISCOVER)
            presenter.downloadMoviesData()

        super.onViewCreated(view, savedInstanceState)

        recycler_movies.apply {
            val linearLayout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            layoutManager = linearLayout
            isNestedScrollingEnabled = false
        }

        class EndlessListener(layout:LinearLayoutManager): EndlessRecyclerViewScrollListener(layout) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                presenter.downloadMoviesDataNextPage()
            }
        }

        recycler_movies.addOnScrollListener(
                EndlessListener(recycler_movies.layoutManager as LinearLayoutManager))

        if (recycler_movies.adapter == null)
            recycler_movies.adapter = adapter
    }

    override fun update(size: Int) {
        adapter.update(size)
    }

}