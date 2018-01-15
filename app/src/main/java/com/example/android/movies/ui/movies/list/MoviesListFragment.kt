package com.example.android.movies.ui.movies.list

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.movies.R
import com.example.android.movies.di.App
import com.example.android.movies.di.movies.home.DaggerMoviesHomeComponent
import com.example.android.movies.di.movies.home.MoviesHomeModule
import com.example.android.movies.di.movies.list.DaggerMoviesListComponent
import com.example.android.movies.di.movies.list.MoviesListModule
import com.example.android.movies.ui.movies.MoviesContract
import com.example.android.movies.ui.movies.home.MoviesHomeActivity
import kotlinx.android.synthetic.main.movies_home_fragment.*
import javax.inject.Inject


class MoviesListFragment : Fragment(), MoviesContract.View {

    @Inject lateinit var presenter: MoviesContract.Presenter
    @Inject lateinit var adapter: MoviesListAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val type = arguments.getInt(MoviesHomeActivity.DOWNLOAD_TYPE_KEY)

        val app : App = activity.application as App

        val component = DaggerMoviesListComponent.builder()
                .appComponent(app.component)
                .moviesListModule(MoviesListModule(this, type))
                .build()

        component.inject(this)

        return inflater!!.inflate(R.layout.movies_list_fragment,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        presenter.downloadMoviesData()
        super.onViewCreated(view, savedInstanceState)

        recycler_movies.apply {
            val linearLayout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            layoutManager = linearLayout
        }

        if (recycler_movies.adapter == null)
            recycler_movies.adapter = adapter
    }


    override fun update(size: Int) {
        adapter.update(size)
    }


}