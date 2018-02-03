package com.example.android.movies.ui.movies.detailed.info

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.movies.R
import com.example.android.movies.App
//import com.example.android.movies.di.movies.detailed.info.DaggerMoviesInfoComponent

//import com.example.android.movies.di.movies.home.MoviesHomeModule
import com.example.android.movies.loadImage
import com.example.android.movies.ui.movies.detailed.MovieDetailsActivity
import kotlinx.android.synthetic.main.movie_details_info_fragment.*
import javax.inject.Inject


class MoviesInfoFragment : Fragment(), MoviesInfoContract.View{

    @Inject lateinit var presenter: MoviesInfoContract.Presenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //val app : App = activity.application as App
//        val component = DaggerMoviesInfoComponent.builder()
//                .appComponent(app.component)
//                .moviesInfoModule(MoviesInfoModule(this))
//                .build()

        //component.inject(this)

        return inflater!!.inflate(R.layout.movie_details_info_fragment,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        presenter.downloadMovieInfo(arguments.getInt(MovieDetailsActivity.MOVIE_ID))
        super.onViewCreated(view, savedInstanceState)
    }

    override fun display(overview: String, posterPath: String, year: String,
                         status: String, userScore: String, voteCount: String,
                         budget: String, revenue: String, runtime: String,
                         genres: String, languages: String,
                         ratingBackgroundColor:Int,ratingTextColor:Int) {
        text_movie_overview.text = overview
        image_poster.loadImage(getString(R.string.image_start_url),posterPath)
        text_budget.text = budget
        text_year.text = year
        text_status.text = status
        text_user_score.text = userScore
        text_vote_count.text = voteCount
        text_revenue.text = revenue
        text_genres.text = genres
        text_runtime.text = runtime
        text_languages.text = languages
        val voteAverageCircle = text_user_score.background as GradientDrawable
        voteAverageCircle.setColor(ratingBackgroundColor)
        text_user_score.setTextColor(ratingTextColor)
    }
}