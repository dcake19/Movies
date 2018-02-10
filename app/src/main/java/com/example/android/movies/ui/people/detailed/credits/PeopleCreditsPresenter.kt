package com.example.android.movies.ui.people.detailed.credits

import com.example.android.movies.BuildConfig
import com.example.android.movies.RxSchedulerProvider
import com.example.android.movies.api.data.people.Cast
import com.example.android.movies.api.data.people.PersonCredits
import com.example.android.movies.util.TextUtil
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class PeopleCreditsPresenter @Inject constructor(val interactor:PeopleCreditsInteractor,
                                                 val rxSchedulerProvider: RxSchedulerProvider)
    : PeopleCreditsContract.Presenter {

    lateinit var view: PeopleCreditsContract.View
   // private lateinit var personCredits:PersonCredits
    private lateinit var cast: List<Cast>
    private lateinit var crew: List<CrewMultipleJobs>
    private var subscribed = false
    private var complete = false

    override fun changeView(view: PeopleCreditsContract.View) {
        this.view = view
    }

    override fun downloadCredits(id: Int) {
        if (complete)
            view.display( cast.size,crew.size)
        else if(!subscribed){
            val observable = interactor.getPersonCredits(id.toString(), BuildConfig.TMDB_API_KEY)
            observable.subscribeOn(rxSchedulerProvider.subscribeOn())
                    .observeOn(rxSchedulerProvider.observeOn())
                    .map { it -> Pair(it.cast,setCrewWithMultipleJobs(it)) }
                    .subscribe(object : Observer<Pair<List<Cast>,List<CrewMultipleJobs>>> {
                        override fun onNext(t: Pair<List<Cast>,List<CrewMultipleJobs>>) {
                            cast = t.first
                            crew = t.second
                            view.display(cast.size,crew.size)
                        }

                        override fun onError(e: Throwable?) {
                            subscribed = false
                        }

                        override fun onComplete() {
                            complete = true
                        }

                        override fun onSubscribe(d: Disposable?) {
                            subscribed = true
                        }
                    })
        }
    }

    private fun setCrewWithMultipleJobs(personCredits: PersonCredits): List<CrewMultipleJobs>{
        val map = HashMap<Int,CrewMultipleJobs>()
        var order = 0
        for(c in personCredits.crew){
            if(map.containsKey(c.id))
                map[c.id]!!.jobs.add(c.job)
            else {
                map.put(c.id, CrewMultipleJobs(c.id, order, c.title,
                        arrayListOf(c.job), c.posterPath ?: "",
                        c.backdropPath?:""))
                order++
            }
        }

        val crewList = map.toList().map {it->it.second}
        return crewList.sortedBy {it.order }
    }

    override fun getCastMovieId(position: Int): Int {
        return cast.get(position).id
    }

    override fun getCastMovieName(position: Int): String {
        return cast.get(position).title
    }

    override fun getCharacter(position: Int): String {
        return cast.get(position).character
    }

    override fun getCastMoviePosterPath(position: Int): String {
        return cast.get(position).posterPath?:""
    }

    override fun getCrewMovieId(position: Int): Int {
        return crew.get(position).id
    }

    override fun getCrewMovieName(position: Int): String {
        return crew.get(position).title
    }

    override fun getJob(position: Int): String {
        return TextUtil.convertToCommaSeparatedString("",
                crew.get(position).jobs,{it->it})
    }

    override fun getCrewMoviePosterPath(position: Int): String {
        return crew.get(position).posterPath?:""
    }

    override fun getCastMovieBackdropPath(position: Int): String {
        return cast[position].backdropPath?:""
    }

    override fun getCrewMovieBackdropPath(position: Int): String {
        return crew[position].backdropPath?:""
    }
}

data class CrewMultipleJobs(val id:Int,
                            val order:Int,
                            val title:String,
                            var jobs:ArrayList<String>,
                            val posterPath:String,
                            val backdropPath:String)