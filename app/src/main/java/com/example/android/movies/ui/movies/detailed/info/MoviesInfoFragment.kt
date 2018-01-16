package com.example.android.movies.ui.movies.detailed.info

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.movies.R
import com.example.android.movies.di.App
import com.example.android.movies.di.movies.detailed.info.DaggerMoviesInfoComponent
import com.example.android.movies.di.movies.detailed.info.MoviesInfoModule

import com.example.android.movies.di.movies.home.MoviesHomeModule
import com.example.android.movies.loadImage
import com.example.android.movies.ui.movies.detailed.MovieDetailsActivity
import kotlinx.android.synthetic.main.movie_details_info_fragment.*
import javax.inject.Inject


class MoviesInfoFragment : Fragment(), MoviesInfoContract.View{

    @Inject lateinit var presenter: MoviesInfoContract.Presenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val app : App = activity.application as App
        val component = DaggerMoviesInfoComponent.builder()
                .appComponent(app.component)
                .moviesInfoModule(MoviesInfoModule(this))
                .build()

        component.inject(this)

        return inflater!!.inflate(R.layout.movie_details_info_fragment,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        presenter.downloadMovieInfo(arguments.getInt(MovieDetailsActivity.MOVIE_ID))
        super.onViewCreated(view, savedInstanceState)
    }

    override fun display(title: String, overview: String, posterPath: String) {
        text_movie_overview.text = overview
        image_poster.loadImage(getString(R.string.image_start_url),posterPath)
    }
}