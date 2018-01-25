package com.example.android.movies.presenters

import android.content.Context
import android.support.v4.content.ContextCompat
import com.example.android.movies.BuildConfig
import com.example.android.movies.R
import com.example.android.movies.RxSchedulerProvider
import com.example.android.movies.api.MoviesApi
import com.example.android.movies.api.data.movie.*
import com.example.android.movies.ui.movies.detailed.info.MoviesInfoContract
import com.example.android.movies.ui.movies.detailed.info.MoviesInfoInteractor
import com.example.android.movies.ui.movies.detailed.info.MoviesInfoPresenter
import com.example.android.movies.util.ColorUtil
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.api.mockito.PowerMockito



@RunWith(PowerMockRunner::class)
@PrepareForTest(ColorUtil::class,ContextCompat::class)
class MoviesInfoPresenterTests {

    @Mock
    internal var view : MoviesInfoContract.View? = null

    @Mock
    internal var context: Context? = null

    @Mock
    val api: MoviesApi? = null

    lateinit var presenter: MoviesInfoPresenter

    val movieInfo = MovieInfo(false,null,null,
            10000000, arrayListOf(Genre(80,"Crime"),
            Genre(18,"Drama")),null,1,null,
            "English","Original Title",
            "Overview",6.5, "posterPath",
            arrayListOf(ProductionCompany("pc1",2)),
            arrayListOf(ProductionCountry("","")),
            "2003-10-02",15000000,120,
            arrayListOf(SpokenLanguage("","English")),"Released",
            null,"Title",true,7.8,500)

    @Before
    fun init(){
        MockitoAnnotations.initMocks(this)

        mockMethods()

        val rxSchedulerProvider = object : RxSchedulerProvider {
            override fun subscribeOn(): Scheduler {
                return Schedulers.trampoline()
            }
            override fun observeOn(): Scheduler {
                return Schedulers.trampoline()
            }
        }
        Mockito.`when`(api!!.getMovieInfo("1",BuildConfig.TMDB_API_KEY))
                .thenReturn(getObservable())

        presenter = MoviesInfoPresenter(MoviesInfoInteractor(api!!),rxSchedulerProvider,view!!)
    }

    @Test
    fun download(){
        PowerMockito.mockStatic(ColorUtil::class.java)
        PowerMockito.mockStatic(ContextCompat::class.java)
        `when`(ColorUtil.getRatingBackgroundColor(
                context!!,movieInfo.voteAverage)).thenReturn(0)
        `when`(ContextCompat.getColor(context!!,R.color.colorUserScoreVeryGood))
                .thenReturn(0)

        presenter.downloadMovieInfo(1)
        verify(view)!!.display(movieInfo.overview?:"",
                movieInfo.posterPath?:"",
                "2003",
                movieInfo.status,
                movieInfo.voteAverage.toString(),
                movieInfo.voteCount.toString() + " " + "votes",
                "Budget: $10m",
                "Revenue: $15m",
                "Runtime: 120 mins",
                "Genre(s): Crime, Drama",
                "Language(s): English",
                0,0)
    }

    fun getObservable(): Observable<MovieInfo> {

        return Observable.create(object : ObservableOnSubscribe<MovieInfo> {
            override fun subscribe(e: ObservableEmitter<MovieInfo>?) {
                e!!.onNext(movieInfo)
            }
        })
    }

    private fun mockMethods(){
        `when`(view!!.getContext()).thenReturn(context)
        `when`(context!!.getString(R.string.vote)).thenReturn("vote")
        `when`(context!!.getString(R.string.votes)).thenReturn("votes")
        `when`(context!!.getString(R.string.budget)).thenReturn("Budget:")
        `when`(context!!.getString(R.string.revenue)).thenReturn("Revenue:")
        `when`(context!!.getString(R.string.runtime)).thenReturn("Runtime:")
        `when`(context!!.getString(R.string.mins)).thenReturn("mins")
        `when`(context!!.getString(R.string.genres)).thenReturn("Genre(s):")
        `when`(context!!.getString(R.string.languages)).thenReturn("Language(s):")

    }
}