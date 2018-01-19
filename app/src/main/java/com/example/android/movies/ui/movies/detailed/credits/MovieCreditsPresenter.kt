package com.example.android.movies.ui.movies.detailed.credits

import android.util.Log
import com.example.android.movies.BuildConfig
import com.example.android.movies.api.data.movie.MovieCredits
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieCreditsPresenter(val interactor: MovieCreditsInteractor): MovieCreditsContract.Presenter {

    lateinit var view: MovieCreditsContract.View
    private lateinit var movieCredits:MovieCredits
    private var downloadComplete = false
    override fun changeView(view: MovieCreditsContract.View) {
        this.view = view
    }

    override fun downloadCredits(id: Int) {
        Log.v("presenter",hashCode().toString())
        if (downloadComplete)
            view.display( movieCredits.cast.size, movieCredits.crew.size)
        else {
            val observable = interactor.getMovieCredits(id.toString(), BuildConfig.TMDB_API_KEY, "1")
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<MovieCredits> {
                        override fun onNext(t: MovieCredits) {
                            movieCredits = t
                            view.display(movieCredits.cast.size, movieCredits.crew.size)
                        }

                        override fun onError(e: Throwable?) {

                        }

                        override fun onComplete() {
                            downloadComplete = true
                        }

                        override fun onSubscribe(d: Disposable?) {

                        }
                    })
        }
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