package com.example.android.movies.ui.movies.detailed.info

import com.example.android.movies.BuildConfig
import com.example.android.movies.R
import com.example.android.movies.api.data.movie.MovieInfo
import com.example.android.movies.util.ColorUtil
import com.example.android.movies.util.TextUtil
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MoviesInfoPresenter(val interactor: MoviesInfoInteractor,val view: MoviesInfoContract.View)
    : MoviesInfoContract.Presenter {

    private lateinit var movieInfo:MovieInfo

    override fun downloadMovieInfo(id: Int) {
        val observable = interactor.getMovieInfo(id.toString(),BuildConfig.TMDB_API_KEY)
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({mi: MovieInfo -> saveMovieInfo(mi)})
    }

    private fun saveMovieInfo(mi: MovieInfo){
        movieInfo = mi
        view.display(movieInfo.overview?:"",
                movieInfo.posterPath?:"",
                TextUtil.getYearFromDate(movieInfo.releaseDate),
                movieInfo.status,
                movieInfo.voteAverage.toString(),
                movieInfo.voteCount.toString() + " " + getVoteCountString(),
                TextUtil.convertMoney(view.getContext().getString(R.string.budget),movieInfo.budget),
                TextUtil.convertMoney(view.getContext().getString(R.string.revenue),movieInfo.revenue),
                view.getContext().getString(R.string.runtime)+ movieInfo.runtime.toString() +
                        view.getContext().getString(R.string.mins),
                TextUtil.convertToCommaSeparatedString(
                        view.getContext().getString(R.string.genres),movieInfo.genres,{ it->it.name}),
                TextUtil.convertToCommaSeparatedString(
                        view.getContext().getString(R.string.languages),movieInfo.spokenLanguages,{it->it.name}),
                ColorUtil.getRatingBackgroundColor(view.getContext(),movieInfo.voteAverage),
                ColorUtil.getTextColor(view.getContext(),movieInfo.voteAverage))
    }

    private fun getVoteCountString():String{
        if (movieInfo.voteCount == 1) return view.getContext().getString(R.string.vote)
        else if (movieInfo.voteCount > 1) return view.getContext().getString(R.string.votes)
        else return ""
    }

}