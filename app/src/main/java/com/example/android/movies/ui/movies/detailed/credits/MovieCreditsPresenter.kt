package com.example.android.movies.ui.movies.detailed.credits

import android.util.Log
import com.example.android.movies.BuildConfig
import com.example.android.movies.RxSchedulerProvider
import com.example.android.movies.api.data.movie.Cast
import com.example.android.movies.api.data.movie.Crew
import com.example.android.movies.api.data.movie.MovieCredits
import com.example.android.movies.util.TextUtil
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieCreditsPresenter @Inject constructor(val interactor: MovieCreditsInteractor,
                                                val rxSchedulerProvider: RxSchedulerProvider)
    : MovieCreditsContract.Presenter {

    lateinit var view: MovieCreditsContract.View
  //  private lateinit var movieCredits:MovieCredits
    private lateinit var cast: List<Cast>
    private lateinit var crew:List<CrewMultipleJobs>
    private var downloadComplete = false

    override fun changeView(view: MovieCreditsContract.View) {
        this.view = view
//        Log.v("MovieCreditsPresenter",hashCode().toString())
    }

    override fun downloadCredits(id: Int) {
        if (downloadComplete)
            view.display( cast.size,crew.size)
            //view.display( movieCredits.cast.size, movieCredits.crew.size)
        else {
            val observable = interactor.getMovieCredits(id.toString(), BuildConfig.TMDB_API_KEY, "1")
            observable.subscribeOn(rxSchedulerProvider.subscribeOn())
                    .observeOn(rxSchedulerProvider.observeOn())
                    .map { it -> Pair(it.cast,setCrewWithMultipleJobs(it)) }
                    .subscribe(object : Observer<Pair<List<Cast>,List<CrewMultipleJobs>>> {
                        override fun onNext(t: Pair<List<Cast>,List<CrewMultipleJobs>>) {
                            downloadComplete = true
                            cast = t.first
                            crew = t.second
                            //movieCredits = t
                            //cast = t.cast
                           // setCrewWithMultipleJobs(t)
                            //view.display(movieCredits.cast.size, movieCredits.crew.size)
                            view.display(cast.size, crew.size)
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

//    private fun setCrewWithMultipleJobs(movieCredits: MovieCredits){
//        val map = HashMap<Int,CrewMultipleJobs>()
//        var order = 0
//        for(c in movieCredits.crew){
//            if(map.containsKey(c.id))
//                map[c.id]!!.jobs.add(c.job)
//            else {
//                map.put(c.id, CrewMultipleJobs(c.id, order, c.name,
//                        arrayListOf(c.job), c.profilePath ?: ""))
//                order++
//            }
//        }
//
//        val crewList = map.toList().map {it->it.second}
//        crew = crewList.sortedBy {it.order }
//    }

    private fun setCrewWithMultipleJobs(movieCredits: MovieCredits): List<CrewMultipleJobs>{
        val map = HashMap<Int,CrewMultipleJobs>()
        var order = 0
        for(c in movieCredits.crew){
            if(map.containsKey(c.id))
                map[c.id]!!.jobs.add(c.job)
            else {
                map.put(c.id, CrewMultipleJobs(c.id, order, c.name,
                        arrayListOf(c.job), c.profilePath ?: ""))
                order++
            }
        }

        val crewList = map.toList().map {it->it.second}
        return crewList.sortedBy {it.order }
    }

    override fun getCastId(position: Int): Int{
        return cast.get(position).id
    }

    override fun getCrewId(position: Int): Int{
        return crew.get(position).id
    }

    override fun getCastName(position: Int): String {
        return cast.get(position).name
    }

    override fun getCharacter(position: Int): String {
        return cast.get(position).character
    }

    override fun getCastPosterPath(position: Int): String {
        return cast.get(position).profilePath?:""
    }

    override fun getCrewName(position: Int): String {
        return crew.get(position).name
    }

    override fun getJob(position: Int): String {
        return TextUtil.convertToCommaSeparatedString("",
                crew.get(position).jobs,{it->it})
    }

    override fun getCrewPosterPath(position: Int): String {
        return crew.get(position).profilePath?:""
    }


//    override fun getCastId(position: Int): Int{
//        return movieCredits.cast.get(position).id
//    }
//
//    override fun getCrewId(position: Int): Int{
//        return movieCredits.crew.get(position).id
//    }
//
//    override fun getCastName(position: Int): String {
//        return movieCredits.cast.get(position).name
//    }
//
//    override fun getCharacter(position: Int): String {
//        return movieCredits.cast.get(position).character
//    }
//
//    override fun getCastPosterPath(position: Int): String {
//        return movieCredits.cast.get(position).profilePath?:""
//    }
//
//    override fun getCrewName(position: Int): String {
//        return movieCredits.crew.get(position).name
//    }
//
//    override fun getJob(position: Int): String {
//        return movieCredits.crew.get(position).job
//    }
//
//    override fun getCrewPosterPath(position: Int): String {
//        return movieCredits.crew.get(position).profilePath?:""
//    }

}

data class CrewMultipleJobs(val id:Int,
                            val order:Int,
                            val name:String,
                            var jobs:ArrayList<String>,
                            val profilePath:String)