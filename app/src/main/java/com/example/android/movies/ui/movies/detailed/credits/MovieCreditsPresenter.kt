package com.example.android.movies.ui.movies.detailed.credits

import android.util.Log
import com.example.android.movies.BuildConfig
import com.example.android.movies.RxSchedulerProvider
import com.example.android.movies.api.data.movie.MovieCredits
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieCreditsPresenter @Inject constructor(val interactor: MovieCreditsInteractor,
                                                val rxSchedulerProvider: RxSchedulerProvider)
    : MovieCreditsContract.Presenter {

    lateinit var view: MovieCreditsContract.View
    private lateinit var movieCredits:MovieCredits
    private var downloadComplete = false

    override fun changeView(view: MovieCreditsContract.View) {
        this.view = view
        Log.v("MovieCreditsPresenter",hashCode().toString())
    }

    override fun downloadCredits(id: Int) {
        if (downloadComplete)
            view.display( movieCredits.cast.size, movieCredits.crew.size)
        else {
            val observable = interactor.getMovieCredits(id.toString(), BuildConfig.TMDB_API_KEY, "1")
            observable.subscribeOn(rxSchedulerProvider.subscribeOn())
                    .observeOn(rxSchedulerProvider.observeOn())
                    .subscribe(object : Observer<MovieCredits> {
                        override fun onNext(t: MovieCredits) {
                            downloadComplete = true
                            movieCredits = t
                            view.display(movieCredits.cast.size, movieCredits.crew.size)
                        }

                        override fun onError(e: Throwable?) {

                        }

                        override fun onComplete() {

                        }

                        override fun onSubscribe(d: Disposable?) {

                        }
                    })
        }
    }

    override fun getCastId(position: Int): Int{
        return movieCredits.cast.get(position).id
    }

    override fun getCrewId(position: Int): Int{
        return movieCredits.crew.get(position).id
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

    override fun getCrewName(position: Int): String {
        return movieCredits.crew.get(position).name
    }

    override fun getJob(position: Int): String {
        return movieCredits.crew.get(position).job
    }

    override fun getCrewPosterPath(position: Int): String {
        return movieCredits.crew.get(position).profilePath?:""
    }
}