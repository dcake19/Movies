package com.example.android.movies.presenters

import android.content.Context
import com.example.android.movies.BuildConfig
import com.example.android.movies.R
import com.example.android.movies.RxSchedulerProvider
import com.example.android.movies.api.MoviesApi
import com.example.android.movies.api.data.movie.MovieResults
import com.example.android.movies.api.data.movie.Result
import com.example.android.movies.ui.movies.MoviesContract
import com.example.android.movies.ui.movies.MoviesDownloadTypes
import com.example.android.movies.ui.movies.MoviesInteractor
import com.example.android.movies.ui.movies.MoviesPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.mockito.Matchers.any
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify


class MoviesPresenterTests {

    @Mock
    internal var view : MoviesContract.View? = null

    @Mock
    internal var context: Context? = null

    @Mock
    val api: MoviesApi? = null

    lateinit var presenter:MoviesPresenter

    val results = ArrayList<Result>(3)
    val results2 = ArrayList<Result>(2)

    @Before
    fun init(){
        MockitoAnnotations.initMocks(this)
        `when`(view!!.getContext()).thenReturn(context)
        `when`(context!!.getString(R.string.vote)).thenReturn("vote")
        `when`(context!!.getString(R.string.votes)).thenReturn("votes")

        val interactor = MoviesInteractor(api!!)

        val rxSchedulerProvider = object : RxSchedulerProvider{
            override fun subscribeOn(): Scheduler {
                return Schedulers.trampoline()
            }
            override fun observeOn(): Scheduler {
                return Schedulers.trampoline()
            }
        }

        presenter = MoviesPresenter(interactor,rxSchedulerProvider)
        presenter.addView(view!!, MoviesDownloadTypes.NOW_PLAYING)
        Mockito.`when`(api.getNowPlayingResults(BuildConfig.TMDB_API_KEY,"1"))
                .thenReturn(getObservable())
        Mockito.`when`(api!!.getNowPlayingResults(BuildConfig.TMDB_API_KEY,"2"))
                .thenReturn(getObservablePage2())
        presenter.downloadMoviesData()
    }

    @Test
    fun download(){
        verify(view)!!.update(3)
        checkPage1()

        presenter.downloadMoviesDataNextPage()

        verify(view)!!.update(5)
        checkPage1()
        checkPage2()

    }

    private fun checkPage1(){
        Assert.assertEquals(results.get(0).id,presenter.getMovieId(0))
        Assert.assertEquals(results.get(1).id,presenter.getMovieId(1))
        Assert.assertEquals(results.get(2).id,presenter.getMovieId(2))

        Assert.assertEquals(results.get(0).posterPath,presenter.getPosterPath(0))
        Assert.assertEquals(results.get(1).posterPath,presenter.getPosterPath(1))
        Assert.assertEquals("",presenter.getPosterPath(2))

        Assert.assertEquals(results.get(0).originalTitle,presenter.getTitle(0))
        Assert.assertEquals(results.get(1).originalTitle,presenter.getTitle(1))
        Assert.assertEquals(results.get(2).originalTitle,presenter.getTitle(2))

        Assert.assertEquals("2017",presenter.getYear(0))
        Assert.assertEquals("2016",presenter.getYear(1))
        Assert.assertEquals("2015",presenter.getYear(2))

        Assert.assertEquals("1 vote",presenter.getVoteCount(0))
        Assert.assertEquals("102 votes",presenter.getVoteCount(1))
        Assert.assertEquals("0 votes",presenter.getVoteCount(2))
    }

    private fun checkPage2(){
        Assert.assertEquals(results2.get(0).id,presenter.getMovieId(3))
        Assert.assertEquals(results2.get(1).id,presenter.getMovieId(4))

        Assert.assertEquals(results2.get(0).posterPath,presenter.getPosterPath(3))
        Assert.assertEquals(results2.get(1).posterPath,presenter.getPosterPath(4))

        Assert.assertEquals(results2.get(0).originalTitle,presenter.getTitle(3))
        Assert.assertEquals(results2.get(1).originalTitle,presenter.getTitle(4))

        Assert.assertEquals("2014",presenter.getYear(3))
        Assert.assertEquals("2013",presenter.getYear(4))

        Assert.assertEquals("200 votes",presenter.getVoteCount(3))
        Assert.assertEquals("300 votes",presenter.getVoteCount(4))
    }

    fun getObservablePage2():Observable<MovieResults>{
        return Observable.create(object : ObservableOnSubscribe<MovieResults>{
            override fun subscribe(e: ObservableEmitter<MovieResults>?) {

                results2.add(Result(200,4,true,7.4,
                        "Title 4",5.4, "posterPath1",
                        "English", "Original Title 4",
                        List(1,{it->18}), "backdropPath",
                        false,"Overview 4","2014-09-15"))
                results2.add(Result(300,5,true,7.5,
                        "Title 5",5.5, "posterPath2",
                        "English", "Original Title 5",
                        List(1,{it->99}), "backdropPath",
                        false,"Overview 5","2013-09-15"))

                val movieResults = MovieResults(2,5,2,results2)

                e!!.onNext(movieResults)
            }
        })
    }


    fun getObservable():Observable<MovieResults>{
        return Observable.create(object : ObservableOnSubscribe<MovieResults>{
            override fun subscribe(e: ObservableEmitter<MovieResults>?) {

                results.add(Result(1,1,true,7.1,
                        "Title 1",5.1, "posterPath1",
                        "English", "Original Title 1",
                        List(1,{it->18}), "backdropPath",
                        false,"Overview 1","2017-09-15"))
                results.add(Result(102,2,true,7.2,
                        "Title 2",5.2, "posterPath2",
                        "English", "Original Title 2",
                        List(1,{it->99}), "backdropPath",
                        false,"Overview 2","2016-09-15"))
                results.add(Result(0,3,true,7.3,
                        "Title 3",5.3, null,
                        "English", "Original Title 3",
                        List(1,{it->80}), "backdropPath",
                        false,"Overview 3","2015-09-15"))

                val movieResults = MovieResults(1,5,2,results)

                e!!.onNext(movieResults)
            }
        })
    }

}