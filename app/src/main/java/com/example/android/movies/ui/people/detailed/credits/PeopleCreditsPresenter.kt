package com.example.android.movies.ui.people.detailed.credits

import com.example.android.movies.BuildConfig
import com.example.android.movies.RxSchedulerProvider
import com.example.android.movies.api.data.people.PersonCredits
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsContract
import com.example.android.movies.ui.people.detailed.credits.PeopleCreditsContract
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class PeopleCreditsPresenter(val interactor:PeopleCreditsInteractor,
                             val rxSchedulerProvider: RxSchedulerProvider)
    : PeopleCreditsContract.Presenter {

    lateinit var view: PeopleCreditsContract.View
    private lateinit var personCredits:PersonCredits
    private var downloadComplete = false


    override fun changeView(view: PeopleCreditsContract.View) {
        this.view = view
    }

    override fun downloadCredits(id: Int) {
        if (downloadComplete)
            view.display( personCredits.cast.size, personCredits.crew.size)
        else {
            val observable = interactor.getPersonCredits(id.toString(), BuildConfig.TMDB_API_KEY)
            observable.subscribeOn(rxSchedulerProvider.subscribeOn())
                    .observeOn(rxSchedulerProvider.observeOn())
                    .subscribe(object : Observer<PersonCredits> {
                        override fun onNext(t: PersonCredits) {
                            personCredits = t
                            view.display(personCredits.cast.size,personCredits.crew.size)
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

    override fun getCastMovieId(position: Int): Int {
        return personCredits.cast.get(position).id
    }

    override fun getCastMovieName(position: Int): String {
        return personCredits.cast.get(position).title
    }

    override fun getCharacter(position: Int): String {
        return personCredits.cast.get(position).character
    }

    override fun getCastMoviePosterPath(position: Int): String {
        return personCredits.cast.get(position).posterPath?:""
    }

    override fun getCrewMovieId(position: Int): Int {
        return personCredits.crew.get(position).id
    }

    override fun getCrewMovieName(position: Int): String {
        return personCredits.crew.get(position).title
    }

    override fun getJob(position: Int): String {
        return personCredits.crew.get(position).job
    }

    override fun getCrewMoviePosterPath(position: Int): String {
        return personCredits.crew.get(position).posterPath?:""
    }

}