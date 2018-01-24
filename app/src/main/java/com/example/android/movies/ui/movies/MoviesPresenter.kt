package com.example.android.movies.ui.movies

import com.example.android.movies.BuildConfig
import com.example.android.movies.R
import com.example.android.movies.api.data.movie.MovieResults
import com.example.android.movies.api.data.movie.Result
import com.example.android.movies.ui.movies.MoviesContract
import com.example.android.movies.ui.movies.MoviesDownloadTypes
import com.example.android.movies.ui.movies.MoviesInteractor
import com.example.android.movies.util.ColorUtil
import com.example.android.movies.util.TextUtil
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MoviesPresenter(val interactor: MoviesInteractor, val view: MoviesContract.View, val type:Int)
    : MoviesContract.Presenter {


    private lateinit var moviesResults:MovieResults
    private var query = ""
    private var discover = DiscoverQuery()

    override fun downloadMoviesData() {
        downloadMoviesData(1)
    }

    override fun downloadMoviesDataNextPage() {
        if (moviesResults.page < moviesResults.totalPages)
            downloadMoviesData(moviesResults.page+1)
    }

    override fun downloadDiscoverData(discoverQuery: DiscoverQuery) {
        discover = discoverQuery
        downloadMoviesData()
    }

//    override fun downloadDiscoverDataNextPage() {
//        if (moviesResults.page < moviesResults.totalPages)
//            downloadMoviesData(moviesResults.page+1)
//    }

    override fun search(query: String) {
        this.query = query
        downloadMoviesData(1)
    }

    fun downloadMoviesData(page:Int=1) {

        val observable: Observable<MovieResults>
        when(type){
            MoviesDownloadTypes.NOW_PLAYING ->
                observable = interactor.getNowPlayingResults(BuildConfig.TMDB_API_KEY,page.toString())
            MoviesDownloadTypes.UPCOMING ->
                observable = interactor.getUpcomingResults(BuildConfig.TMDB_API_KEY,page.toString())
            MoviesDownloadTypes.TOP_RATED ->
                observable = interactor.getTopRatedResults(BuildConfig.TMDB_API_KEY,page.toString())
            MoviesDownloadTypes.POPULAR ->
                observable = interactor.getPopular(BuildConfig.TMDB_API_KEY,page.toString())
            MoviesDownloadTypes.SEARCH ->
                observable = interactor.getSearchResults(BuildConfig.TMDB_API_KEY,query,page.toString())
            MoviesDownloadTypes.DISCOVER ->
                    observable = interactor.getDiscoverResults(BuildConfig.TMDB_API_KEY,discover.sortby,
                            discover.withGenres,discover.withoutGenres, discover.minVoteAverage,
                            discover.minVoteCount,discover.primaryReleaseYear,discover.minRuntime,
                            discover.maxRuntime,page.toString())
            else -> {return}
        }

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<MovieResults> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(mr: MovieResults) {
                        if (page==1) moviesResults = mr
                        else moviesResults = MovieResults(
                                mr.page,mr.totalResults,mr.totalPages,
                                moviesResults.results + mr.results)

                        view.update(moviesResults.results.size)
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
        return moviesResults.results.get(index).posterPath?:""
    }

    override fun getTitle(index: Int): String {
        return moviesResults.results.get(index).originalTitle
    }

    override fun getYear(index: Int): String {
        return TextUtil.getYearFromDate(moviesResults.results.get(index).releaseDate)
    }

    override fun getVoteCount(index: Int): String {
        return moviesResults.results.get(index).voteCount.toString() +
                " " + getVoteCountString(index)
    }

    override fun getVoteAverage(index: Int): String {
        return moviesResults.results.get(index).voteAverage.toString()
    }

    override fun getRatingBackgroundColor(index: Int): Int {
        return ColorUtil.getRatingBackgroundColor(view.getContext(),
                moviesResults.results.get(index).voteAverage)
    }

    override fun getRatingTextColor(index: Int): Int {
        return ColorUtil.getTextColor(view.getContext(),
                moviesResults.results.get(index).voteAverage)
    }

    private fun getVoteCountString(index:Int):String{
        if (moviesResults.results.get(index).voteCount == 1)
            return view.getContext().getString(R.string.vote)
        else if (moviesResults.results.get(index).voteCount > 1)
            return view.getContext().getString(R.string.votes)
        else return ""
    }
}