package com.example.android.movies.ui.movies

import com.example.android.movies.BuildConfig
import com.example.android.movies.api.data.movie.MovieResults
import com.example.android.movies.ui.movies.MoviesContract
import com.example.android.movies.ui.movies.MoviesDownloadTypes
import com.example.android.movies.ui.movies.MoviesInteractor
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MoviesPresenter(val interactor: MoviesInteractor, val view: MoviesContract.View, val type:Int)
    : MoviesContract.Presenter {

    private lateinit var moviesResults:MovieResults

    override fun downloadMoviesData() {
        downloadMoviesData(1)
    }

    override fun downloadMoviesDataNextPage() {
        if (moviesResults.page < moviesResults.totalPages)
            downloadMoviesData(moviesResults.page+1)
    }

    fun downloadMoviesData(page:Int) {

        val observable: Observable<MovieResults>
        when(type){
            MoviesDownloadTypes.NOW_PLAYING ->
                observable = interactor.getNowPlayingResults(BuildConfig.TMDB_API_KEY,page.toString())
            MoviesDownloadTypes.UPCOMING ->
                observable = interactor.getUpcomingResults(BuildConfig.TMDB_API_KEY,page.toString())
            MoviesDownloadTypes.TOP_RATED ->
                observable = interactor.getTopRatedResults(BuildConfig.TMDB_API_KEY,page.toString())
            else -> {return}
        }

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<MovieResults> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(mr: MovieResults) {
                        moviesResults = mr
                        view.update(mr.results.size)
                    }

                    override fun onError(e: Throwable) {
                    }

                    override fun onComplete() {
                    }
                })
    }

    override fun getMovieId(index:Int): Int {
        return moviesResults.results.get(index).id
    }

    override fun getPosterPath(index:Int): String {
        return moviesResults.results.get(index).posterPath
    }

    override fun getTitle(index: Int): String {
        return moviesResults.results.get(index).originalTitle
    }
}