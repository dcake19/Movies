package com.example.android.movies.ui.movies.detailed.videos

import android.content.Intent
import android.net.Uri
import com.example.android.movies.BuildConfig
import com.example.android.movies.R
import com.example.android.movies.RxSchedulerProvider
import com.example.android.movies.api.data.movie.MovieVideos
import com.example.android.movies.api.data.movie.VideoResult
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsInteractor
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class MovieVideosPresenter @Inject constructor(
        val interactor: MovieCreditsInteractor, val rxSchedulerProvider: RxSchedulerProvider)
    : MovieVideosContract.Presenter {

    lateinit var view: MovieVideosContract.View
    private lateinit var videos: List<VideoResult>

    override fun addView(view: MovieVideosContract.View) {
        this.view = view
    }

    override fun downloadVideoLinks(id: Int) {
        val observable = interactor.getVideos(id.toString(), BuildConfig.TMDB_API_KEY)

        observable.subscribeOn(rxSchedulerProvider.subscribeOn())
                .observeOn(rxSchedulerProvider.observeOn())
                .map { it -> it.results.filter { it.site.equals("YouTube") } }
                .subscribe(object : Observer<List<VideoResult>> {
                    override fun onNext(t: List<VideoResult>) {
                        videos = t
                        view.display(t.size)
                    }

                    override fun onError(e: Throwable?) {

                    }

                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable?) {

                    }
                })
    }

    override fun getVideoTitle(position: Int): String {
        return videos[position].name?:""
    }

    override fun launchVideo(position: Int) {
        val webpage = view.getContext().getString(
                R.string.youtube_link_start) + videos[position].key
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(webpage))
        if (intent.resolveActivity(view.getContext().getPackageManager()) != null)
            view.getContext().startActivity(intent)

    }


}