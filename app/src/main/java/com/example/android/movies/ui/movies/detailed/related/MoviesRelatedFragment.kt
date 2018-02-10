package com.example.android.movies.ui.movies.detailed.related

import android.os.Bundle
import android.view.View
import com.example.android.movies.ui.movies.detailed.MovieDetailsActivity
import com.example.android.movies.ui.movies.BaseMoviesListFragment
import com.example.android.movies.util.ColorUtil
import kotlinx.android.synthetic.main.movies_list_fragment.*


class MoviesRelatedFragment: BaseMoviesListFragment(){

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        presenter.downloadRelatedMovies(arguments.getInt(MovieDetailsActivity.MOVIE_ID))
        layout_movies_list.setBackgroundColor(ColorUtil.getLighterShade(
                arguments.getInt(MovieDetailsActivity.MOVIE_BACKGROUND_COLOR)))
        super.onViewCreated(view, savedInstanceState)
    }
}