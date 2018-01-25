package com.example.android.movies.presenters

import com.example.android.movies.BuildConfig
import com.example.android.movies.RxSchedulerProvider
import com.example.android.movies.api.MoviesApi
import com.example.android.movies.api.data.people.Cast
import com.example.android.movies.api.data.people.Crew
import com.example.android.movies.api.data.people.PersonCredits
import com.example.android.movies.ui.people.detailed.credits.PeopleCreditsContract
import com.example.android.movies.ui.people.detailed.credits.PeopleCreditsInteractor
import com.example.android.movies.ui.people.detailed.credits.PeopleCreditsPresenter
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


class PeopleCreditsPresenterTests {

    @Mock
    internal var view : PeopleCreditsContract.View? = null

    @Mock
    val api: MoviesApi? = null

    lateinit var presenter: PeopleCreditsPresenter

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

        Mockito.`when`(api!!.getPersonCredits("1", BuildConfig.TMDB_API_KEY))
                .thenReturn(getObservable())

        presenter = PeopleCreditsPresenter(PeopleCreditsInteractor(api!!),
                rxSchedulerProvider)

        presenter.changeView(view!!)

        setCastAndCrew()
    }

    @Test
    fun download(){
        presenter.downloadCredits(1)
        verify(view)!!.display(3,2)

        Assert.assertEquals(1, presenter.getCastMovieId(0))
        Assert.assertEquals(2, presenter.getCastMovieId(1))
        Assert.assertEquals(3, presenter.getCastMovieId(2))
        Assert.assertEquals(101, presenter.getCrewMovieId(0))
        Assert.assertEquals(102, presenter.getCrewMovieId(1))

        Assert.assertEquals("Title 1", presenter.getCastMovieName(0))
        Assert.assertEquals("Title 2", presenter.getCastMovieName(1))
        Assert.assertEquals("Title 3", presenter.getCastMovieName(2))
        Assert.assertEquals("Title 4", presenter.getCrewMovieName(0))
        Assert.assertEquals("Title 5", presenter.getCrewMovieName(1))

        Assert.assertEquals("character 1", presenter.getCharacter(0))
        Assert.assertEquals("character 2", presenter.getCharacter(1))
        Assert.assertEquals("character 3", presenter.getCharacter(2))
        Assert.assertEquals("job 1", presenter.getJob(0))
        Assert.assertEquals("job 2", presenter.getJob(1))

        Assert.assertEquals("posterPath1", presenter.getCastMoviePosterPath(0))
        Assert.assertEquals("posterPath2", presenter.getCastMoviePosterPath(1))
        Assert.assertEquals("", presenter.getCastMoviePosterPath(2))
        Assert.assertEquals("posterPath4", presenter.getCrewMoviePosterPath(0))
        Assert.assertEquals("", presenter.getCrewMoviePosterPath(1))
    }

    fun setCastAndCrew(){
        cast.add(Cast("character 1","cid1","",1,
                true,false,5.5,"Title 1",
                arrayListOf(18,80),"","Title 1",5.5,
                1,null,"overview 1","posterPath1"))
        cast.add(Cast("character 2","cid2","",1,
                true,false,5.5,"Title 2",
                arrayListOf(18,80),"","Title 2",5.5,
                2,null,"overview 2","posterPath2"))
        cast.add(Cast("character 3","cid3","",1,
                true,false,5.5,"Title 3",
                arrayListOf(18,80),"","Title 3",5.5,
                3,null,"overview 3",null))
        crew.add(Crew(101,"department 1","",
                "Title 4","job 1","Overview 4",40,
                true,"posterPath4",null,"Title 4",
                7.5,arrayListOf(18,80),7.6,false,
                "",""))
        crew.add(Crew(102,"department 2","",
                "Title 5","job 2","Overview 5",50,
                true,null,null,"Title 5",
                7.5,arrayListOf(18,80),7.6,false,
                "",""))
    }

    fun getObservable(): Observable<PersonCredits> {
        return Observable.create(object : ObservableOnSubscribe<PersonCredits> {
            override fun subscribe(e: ObservableEmitter<PersonCredits>?) {
                e!!.onNext(PersonCredits(cast,crew,1000))
            }
        })
    }
}