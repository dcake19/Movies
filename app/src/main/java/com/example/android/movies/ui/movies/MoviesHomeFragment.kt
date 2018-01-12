package com.example.android.movies.ui.movies

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.movies.R
import com.example.android.movies.di.App
import com.example.android.movies.di.movies.DaggerMoviesHomeComponent
import com.example.android.movies.di.movies.MoviesHomeModule
import kotlinx.android.synthetic.main.movies_home_fragment.*
import javax.inject.Inject

class MoviesHomeFragment :Fragment(), MoviesContract.View{

    @Inject lateinit var presenter: MoviesContract.Presenter
    @Inject lateinit var adapter: MoviesAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val type = arguments.getInt(MoviesHomeActivity.DOWNLOAD_TYPE_KEY)

        val app : App = activity.application as App

        val component = DaggerMoviesHomeComponent.builder()
                .appComponent(app.component)
                .moviesHomeModule(MoviesHomeModule(this,type))
                .build()

        component.inject(this)

        return inflater!!.inflate(R.layout.movies_home_fragment,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        presenter.downloadMoviesData()
        super.onViewCreated(view, savedInstanceState)
        val type = arguments.getInt(MoviesHomeActivity.DOWNLOAD_TYPE_KEY)
        setTitleText(type)

        recycler_movies.apply {
            val linearLayout = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            layoutManager = linearLayout
        }

        if (recycler_movies.adapter == null)
            recycler_movies.adapter = adapter
    }

    private fun setTitleText(type:Int){
        when(type){
            MoviesDownloadTypes.NOW_PLAYING -> text_title_type.text = context.getString(R.string.now_playing)
            MoviesDownloadTypes.UPCOMING -> text_title_type.text = context.getString(R.string.upcoming)
            MoviesDownloadTypes.TOP_RATED -> text_title_type.text = context.getString(R.string.top_rated)
        }
    }

    override fun update(size: Int) {
        adapter.update(size)
    }

}