package com.example.android.movies.ui.movies.detailed.credits

import android.os.Bundle
import android.app.Fragment
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.movies.R
import com.example.android.movies.di.ActivityScope
//import com.example.android.movies.di.movies.detailed.credits.DaggerMovieCreditsComponent
import com.example.android.movies.ui.movies.detailed.MovieDetailsActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.movie_details_cast_fragment.*
import javax.inject.Inject

@ActivityScope
class MovieCreditsFragment : Fragment(), MovieCreditsContract.View{

    companion object {
        const val CREDITS_TYPE = "credits_type"
    }

    @Inject lateinit var presenter: MovieCreditsContract.Presenter
   // @Inject
    lateinit var adapter: MovieCreditsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter.changeView(this)
        adapter = MovieCreditsAdapter(presenter,arguments.getInt(CREDITS_TYPE),
                arguments.getInt(MovieDetailsActivity.MOVIE_INDICATOR_COLOR))
        return inflater!!.inflate(R.layout.movie_details_cast_fragment,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        presenter.downloadCredits(arguments.getInt(MovieDetailsActivity.MOVIE_ID))
        super.onViewCreated(view, savedInstanceState)

        recycler_credits.apply {
            layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            isNestedScrollingEnabled = false
        }

        if (recycler_credits.adapter == null)
            recycler_credits.adapter = adapter
    }

    override fun display(sizeCast: Int, sizeCrew: Int) {
       adapter.display(sizeCast,sizeCrew)
    }

}