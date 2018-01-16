package com.example.android.movies.ui.movies.detailed.credits

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.movies.R
import com.example.android.movies.di.App
import com.example.android.movies.di.movies.detailed.credits.DaggerMovieCreditsComponent
import com.example.android.movies.di.movies.detailed.credits.MovieCreditsModule
import com.example.android.movies.ui.movies.detailed.MovieDetailsActivity
import kotlinx.android.synthetic.main.movie_details_cast_fragment.*
import javax.inject.Inject

class MovieCreditsFragment : Fragment(), MovieCreditsContract.View{

    @Inject lateinit var presenter: MovieCreditsContract.Presenter
    @Inject lateinit var adapter: MovieCreditsAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val app : App = activity.application as App

        val component = DaggerMovieCreditsComponent.builder()
                .appComponent(app.component)
                .movieCreditsModule(MovieCreditsModule(this))
                .build()

        component.inject(this)

        return inflater!!.inflate(R.layout.movie_details_cast_fragment,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        presenter.downloadCredits(arguments.getInt(MovieDetailsActivity.MOVIE_ID))
        super.onViewCreated(view, savedInstanceState)

        recycler_credits.apply {
            val linearLayout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            layoutManager = linearLayout
        }

        if (recycler_credits.adapter == null)
            recycler_credits.adapter = adapter
    }

    override fun display(sizeCast: Int, sizeCrew: Int) {
       adapter.display(sizeCast,sizeCrew)
    }

}