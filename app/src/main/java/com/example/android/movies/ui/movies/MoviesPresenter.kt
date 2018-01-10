package com.example.android.movies.ui.movies

import com.example.android.movies.BuildConfig
import com.example.android.movies.api.data.movie.MovieResults
import com.example.android.movies.api.data.movie.Result
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MoviesPresenter(val interactor: MoviesInteractor,val view:MoviesContract.View,val type:Int): MoviesContract.Presenter {
    private var movies:ArrayList<MovieResult> = arrayListOf<MovieResult>()
    private var page = 0
    private var morePages = true

    override fun downloadMoviesData() {
        if (!morePages) return

        page++
        val observable: Observable<MovieResults>
        when(type){
            MoviesDownloadTypes.NOW_PLAYING -> observable = interactor.getNowPlayingResults(BuildConfig.TMDB_API_KEY,page.toString())
            MoviesDownloadTypes.UPCOMING -> observable = interactor.getUpcomingResults(BuildConfig.TMDB_API_KEY,page.toString())
            MoviesDownloadTypes.TOP_RATED -> observable = interactor.getTopRatedResults(BuildConfig.TMDB_API_KEY,page.toString())
            else -> {return}
        }

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<MovieResults> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(movieResults: MovieResults) {
                        movieResults.results!!.forEach{ r-> movies.add(MovieResult(
                           r!!.title?:"",
                            r.overview?:"",
                            r.voteAverage?:0.0,
                            r.voteCount?:0,
                            r.posterPath?:""))}
                        page = movieResults.page?:0
                        if(movieResults.totalPages!=null && movieResults.page!=null)
                            morePages = movieResults.totalPages>movieResults.page
                        else
                            morePages = false
                        view.update(movieResults.results.size)
                    }

                    override fun onError(e: Throwable) {
                    }

                    override fun onComplete() {
                    }
                })

    }


    override fun getPosterPath(index:Int): String {
        return movies[index].posterPath
    }

}

data class MovieResult(val title:String,val overview:String,
                       val voteAverage:Double,val voteCount:Int,
                       val posterPath:String)