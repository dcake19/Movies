package com.example.android.movies.ui.movies.home

import android.os.Bundle
import android.app.Fragment
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.movies.R
//import com.example.android.movies.di.movies.home.DaggerMoviesHomeComponent
//import com.example.android.movies.di.movies.home.MoviesHomeModule
import com.example.android.movies.ui.movies.MoviesContract
import com.example.android.movies.ui.movies.MoviesDownloadTypes
import com.example.android.movies.ui.movies.list.MoviesListActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.movies_home_fragment.*
import javax.inject.Inject

class MoviesHomeFragment :Fragment(), MoviesContract.View {

    @Inject lateinit var presenter: MoviesContract.Presenter
  // @Inject
    lateinit var adapter: MoviesHomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        presenter.addView(this, arguments.getInt(MoviesHomeActivity.DOWNLOAD_TYPE_KEY))
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        adapter = MoviesHomeAdapter(presenter)
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

        text_see_all.setOnClickListener{
            startActivity(MoviesListActivity.getIntent(context,type))}

    }

    private fun setTitleText(type:Int){
        when(type){
            MoviesDownloadTypes.NOW_PLAYING -> text_title_type.text = context.getString(R.string.now_playing)
            MoviesDownloadTypes.UPCOMING -> text_title_type.text = context.getString(R.string.upcoming)
            MoviesDownloadTypes.TOP_RATED -> text_title_type.text = context.getString(R.string.top_rated)
            MoviesDownloadTypes.POPULAR -> text_title_type.text = context.getString(R.string.popular)
        }
    }

    override fun update(size: Int) {
        adapter.update(size)
    }

}