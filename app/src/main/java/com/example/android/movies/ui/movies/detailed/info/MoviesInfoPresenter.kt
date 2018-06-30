package com.example.android.movies.ui.movies.detailed.info

import android.view.View
import com.example.android.movies.BuildConfig
import com.example.android.movies.R
import com.example.android.movies.RxSchedulerProvider
import com.example.android.movies.api.data.movie.MovieInfo
import com.example.android.movies.db.FavoriteMovies
import com.example.android.movies.util.ColorUtil
import com.example.android.movies.util.TextUtil
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe




class MoviesInfoPresenter @Inject constructor(val interactor: MoviesInfoInteractor,
                          val rxSchedulerProvider: RxSchedulerProvider)
                         // val view: MoviesInfoContract.View)
    : MoviesInfoContract.Presenter {

    private lateinit var movieInfo:MovieInfo
    lateinit var view:MoviesInfoContract.View
    private var favorite = false

    private var subscribed = false
    private var complete = false

    override fun addView(view: MoviesInfoContract.View) {
        this.view = view
    }

    override fun downloadMovieInfo(id: Int) {
        if (complete)
            display()
        else if(!subscribed) {
            val observable = interactor.getMovieInfo(id.toString(), BuildConfig.TMDB_API_KEY)
            observable.subscribeOn(rxSchedulerProvider.subscribeOn())
                    .observeOn(rxSchedulerProvider.observeOn())
                    .subscribe(object : Observer<MovieInfo> {
                        override fun onError(e: Throwable?) {
                            subscribed = false
                        }

                        override fun onSubscribe(d: Disposable?) {
                            subscribed = true
                        }

                        override fun onComplete() {
                            complete = true
                        }

                        override fun onNext(t: MovieInfo) {
                            movieInfo = t
                            display()
                        }
                    })
        }
    }

    override fun setFavorite(id:Int) {
        val observable = Observable.create<MutableList<FavoriteMovies>> { e ->
            e.onNext(interactor.getAll())
        }

        val observer = object: Observer<MutableList<FavoriteMovies>>{
            override fun onComplete() {

            }

            override fun onError(e: Throwable?) {

            }

            override fun onNext(favorites: MutableList<FavoriteMovies>?) {
                if (favorites!=null){
                    for (f in favorites){
                        if (f.tmdbId==id){
                            favorite = true
                            break
                        }
                    }
                    view.setFavorite()
                }
            }

            override fun onSubscribe(d: Disposable?) {

            }

        }

        observable.subscribeOn(rxSchedulerProvider.subscribeOn())
                .observeOn(rxSchedulerProvider.observeOn())
                .subscribe (observer)
    }

    override fun isFavorite(): Boolean {
        return  favorite
    }

    private fun display(){
        view.display(movieInfo.overview?:"",
                movieInfo.posterPath?:"",
                TextUtil.getYearFromDate(movieInfo.releaseDate),
                movieInfo.status, getVoteAverageString(),
                movieInfo.voteCount.toString() + " " + getVoteCountString(),
                TextUtil.convertMoney(view.getContext().getString(R.string.budget),movieInfo.budget),
                TextUtil.convertMoney(view.getContext().getString(R.string.revenue),movieInfo.revenue),
                view.getContext().getString(R.string.runtime) + movieInfo.runtime.toString() +
                        " " + view.getContext().getString(R.string.mins),
                TextUtil.convertToCommaSeparatedString(
                        view.getContext().getString(R.string.genres),movieInfo.genres,{ it->it.name}),
                TextUtil.convertToCommaSeparatedString(
                        view.getContext().getString(R.string.languages),movieInfo.spokenLanguages,{it->it.name}),
                ColorUtil.getRatingBackgroundColor(view.getContext(),movieInfo.voteAverage),
                ColorUtil.getTextColor(view.getContext(),movieInfo.voteAverage))
    }

    private fun getVoteAverageString():String{
        if (movieInfo.voteAverage>=10)
            return "10"
        return movieInfo.voteAverage.toString()
    }

    private fun getVoteCountString():String{
        if (movieInfo.voteCount == 1) return view.getContext().getString(R.string.vote)
        else if (movieInfo.voteCount > 1) return view.getContext().getString(R.string.votes)
        else return ""
    }

    override fun changeFavorite() {
        if (favorite){
            favorite = false
            Thread {
                val fm = FavoriteMovies()
                fm.tmdbId = movieInfo.id
                interactor.deleteMovie(fm)
            }.start()

        }else{
            favorite = true
            Thread {
                val fm = FavoriteMovies()
                fm.tmdbId = movieInfo.id
                interactor.insert(fm)
            }.start()
        }
    }
}