package com.example.android.movies.presenters

import android.content.Context
import com.example.android.movies.BuildConfig
import com.example.android.movies.RxSchedulerProvider
import com.example.android.movies.api.MoviesApi
import com.example.android.movies.api.data.movie.Cast
import com.example.android.movies.api.data.movie.Crew
import com.example.android.movies.api.data.movie.MovieCredits
import com.example.android.movies.ui.movies.MoviesContract
import com.example.android.movies.ui.movies.MoviesPresenter
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsContract
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsInteractor
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsPresenter
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MoviesCreditPresenterTests {

    @Mock
    internal var view : MovieCreditsContract.View? = null

    @Mock
    val api: MoviesApi? = null

    lateinit var presenter: MovieCreditsPresenter

    val cast = ArrayList<Cast>(3)
    val crew = ArrayList<Crew>(2)

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)

        val rxSchedulerProvider = object : RxSchedulerProvider {
            override fun subscribeOn(): Scheduler {
                return Schedulers.trampoline()
            }
            override fun observeOn(): Scheduler {
                return Schedulers.trampoline()
            }
        }

        Mockito.`when`(api!!.getMovieCredits("1",BuildConfig.TMDB_API_KEY,"1"))
                .thenReturn(getObservable())

        presenter = MovieCreditsPresenter(MovieCreditsInteractor(api!!),
                rxSchedulerProvider)

        presenter.changeView(view!!)

        setCastAndCrew()
    }

    @Test
    fun download(){
        presenter.downloadCredits(1)
        verify(view)!!.display(3,2)

        assertEquals(1,presenter.getCastId(0))
        assertEquals(2,presenter.getCastId(1))
        assertEquals(3,presenter.getCastId(2))
        assertEquals(4,presenter.getCrewId(0))
        assertEquals(5,presenter.getCrewId(1))

        assertEquals("name 1",presenter.getCastName(0))
        assertEquals("name 2",presenter.getCastName(1))
        assertEquals("name 3",presenter.getCastName(2))
        assertEquals("name 4",presenter.getCrewName(0))
        assertEquals("name 5",presenter.getCrewName(1))

        assertEquals("character 1",presenter.getCharacter(0))
        assertEquals("character 2",presenter.getCharacter(1))
        assertEquals("character 3",presenter.getCharacter(2))
        assertEquals("job 1",presenter.getJob(0))
        assertEquals("job 2",presenter.getJob(1))

        assertEquals("profilePath1",presenter.getCastPosterPath(0))
        assertEquals("profilePath2",presenter.getCastPosterPath(1))
        assertEquals("",presenter.getCastPosterPath(2))
        assertEquals("profilePath4",presenter.getCrewPosterPath(0))
        assertEquals("",presenter.getCrewPosterPath(1))
    }

    private fun setCastAndCrew(){
        cast.add(Cast(100,"character 1","100",
                null,1,"name 1",1,"profilePath1"))
        cast.add(Cast(101,"character 2","101",
                null,2,"name 2",2,"profilePath2"))
        cast.add(Cast(102,"character 3","102",
                null,3,"name 3",3,null))

        crew.add(Crew("201","department 1",null,
                4,"job 1","name 4","profilePath4"))
        crew.add(Crew("202","department 2",null,
                5,"job 2","name 5",null))
    }


    fun getObservable(): Observable<MovieCredits> {
        return Observable.create(object : ObservableOnSubscribe<MovieCredits> {
            override fun subscribe(e: ObservableEmitter<MovieCredits>?) {
                e!!.onNext(MovieCredits(1000,cast,crew))
            }
        })
    }

}