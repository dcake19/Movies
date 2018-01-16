package com.example.android.movies.ui.movies.detailed.info

import com.example.android.movies.BuildConfig
import com.example.android.movies.api.data.movie.MovieInfo
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
        view.display(mi.title,mi.overview,mi.posterPath)
    }

}