package com.example.android.movies.ui.movies.detailed.credits

import android.util.Log
import com.example.android.movies.BuildConfig
import com.example.android.movies.api.data.movie.MovieCredits
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MovieCreditsPresenter(val interactor: MovieCreditsInteractor, val view: MovieCreditsContract.View):
        MovieCreditsContract.Presenter {

    private lateinit var movieCredits:MovieCredits

    override fun downloadCredits(id: Int) {
        val observable = interactor.getMovieCredits(id.toString(), BuildConfig.TMDB_API_KEY,"1")
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<MovieCredits>{
                    override fun onNext(t: MovieCredits) {
                        movieCredits = t
                        view.display(t.cast.size,t.crew.size)
                    }

                    override fun onError(e: Throwable?) {

                    }

                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable?) {

                    }
                })
    }

    override fun getCastName(position: Int): String {
        return movieCredits.cast.get(position).name
    }

    override fun getCharacter(position: Int): String {
        return movieCredits.cast.get(position).character
    }

    override fun getCastPosterPath(position: Int): String {
        return movieCredits.cast.get(position).profilePath?:""
    }

    override fun getCrewtName(position: Int): String {
        return movieCredits.crew.get(position).name
    }

    override fun getJob(position: Int): String {
        return movieCredits.crew.get(position).job
    }

    override fun getCrewPosterPath(position: Int): String {
        return movieCredits.crew.get(position).profilePath?:""
    }
}