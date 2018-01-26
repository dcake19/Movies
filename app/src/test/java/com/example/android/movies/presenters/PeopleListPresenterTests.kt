package com.example.android.movies.presenters

import android.content.Context
import com.example.android.movies.BuildConfig
import com.example.android.movies.R
import com.example.android.movies.RxSchedulerProvider
import com.example.android.movies.api.MoviesApi
import com.example.android.movies.api.data.people.KnownFor
import com.example.android.movies.api.data.people.PersonResults
import com.example.android.movies.api.data.people.Result
import com.example.android.movies.ui.people.list.PeopleListContract
import com.example.android.movies.ui.people.list.PeopleListInteractor
import com.example.android.movies.ui.people.list.PeopleListPresenter
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
import org.mockito.MockitoAnnotations


class PeopleListPresenterTests {

    @Mock
    internal var view : PeopleListContract.View? = null

    @Mock
    internal var context: Context? = null

    @Mock
    val api: MoviesApi? = null

    lateinit var presenter:PeopleListPresenter

    val results = ArrayList<Result>(3)
    val results2 = ArrayList<Result>(2)

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        val interactor = PeopleListInteractor(api!!)

        val rxSchedulerProvider = object : RxSchedulerProvider {
            override fun subscribeOn(): Scheduler {
                return Schedulers.trampoline()
            }
            override fun observeOn(): Scheduler {
                return Schedulers.trampoline()
            }
        }

        presenter = PeopleListPresenter(interactor,rxSchedulerProvider,view!!)

        Mockito.`when`(api.getPopularPeople(BuildConfig.TMDB_API_KEY,"1"))
                .thenReturn(getObservablePage1())
        Mockito.`when`(api.getPopularPeople(BuildConfig.TMDB_API_KEY,"2"))
                .thenReturn(getObservablePage2())
        Mockito.`when`(view!!.getContext()).thenReturn(context)
        Mockito.`when`(context!!.getString(R.string.known_for)).thenReturn("Known for:")
    }

    @Test
    fun download() {
        presenter.downloadPopularPeople()
        Mockito.verify(view)!!.display(3)
        assertpage1()

        presenter.downloadPeopleNextPage()
        Mockito.verify(view)!!.display(5)
        assertpage1()
        assertPage2()
    }

    private fun assertpage1(){
        Assert.assertEquals(results.get(0).id,presenter.getPersonId(0))
        Assert.assertEquals(results.get(1).id,presenter.getPersonId(1))
        Assert.assertEquals(results.get(2).id,presenter.getPersonId(2))

        Assert.assertEquals(results.get(0).profilePath,presenter.getPosterPath(0))
        Assert.assertEquals(results.get(1).profilePath,presenter.getPosterPath(1))
        Assert.assertEquals("",presenter.getPosterPath(2))

        Assert.assertEquals(results.get(0).name,presenter.getPersonName(0))
        Assert.assertEquals(results.get(1).name,presenter.getPersonName(1))
        Assert.assertEquals(results.get(2).name,presenter.getPersonName(2))

        Assert.assertEquals("Known for: Title 1, Title 2, Title 3",
                presenter.getPersonKnownFor(0))
        Assert.assertEquals("Known for: Title 1, Title 4, Title 5",
                presenter.getPersonKnownFor(1))
        Assert.assertEquals("Known for: Title 5, Title 6",
                presenter.getPersonKnownFor(2))
    }

    private fun assertPage2(){
        Assert.assertEquals(results2.get(0).id,presenter.getPersonId(3))
        Assert.assertEquals(results2.get(1).id,presenter.getPersonId(4))

        Assert.assertEquals(results2.get(0).profilePath,presenter.getPosterPath(3))
        Assert.assertEquals(results2.get(1).profilePath,presenter.getPosterPath(4))

        Assert.assertEquals(results2.get(0).name,presenter.getPersonName(3))
        Assert.assertEquals(results2.get(1).name,presenter.getPersonName(4))

        Assert.assertEquals("Known for: Title 3",
                presenter.getPersonKnownFor(3))
        Assert.assertEquals("",
                presenter.getPersonKnownFor(4))
    }

    fun getObservablePage1():Observable<PersonResults>{
        return Observable.create(object : ObservableOnSubscribe<PersonResults> {
            override fun subscribe(e: ObservableEmitter<PersonResults>?) {

                results.add(Result(7.1,1,"profilePath1",
                        "name 1", arrayListOf(getKnownFor(1),
                        getKnownFor(2),getKnownFor(3)),false))
                results.add(Result(7.1,1,"profilePath2",
                        "name 2", arrayListOf(getKnownFor(1),
                        getKnownFor(4),getKnownFor(5)),false))
                results.add(Result(7.1,1,null,
                        "name 3", arrayListOf(getKnownFor(5),
                        getKnownFor(6)),false))
                val movieResults = PersonResults(1,5,2,results)

                e!!.onNext(movieResults)
            }
        })
    }

    fun getObservablePage2():Observable<PersonResults>{
        return Observable.create(object : ObservableOnSubscribe<PersonResults> {
            override fun subscribe(e: ObservableEmitter<PersonResults>?) {

                results2.add(Result(7.1,1,"profilePath4",
                        "name 4", arrayListOf(getKnownFor(3)),false))
                results2.add(Result(7.1,1,"profilePath5",
                        "name 5", arrayListOf(),false))
                val movieResults = PersonResults(1,5,2,results2)

                e!!.onNext(movieResults)
            }
        })
    }

    private fun getKnownFor(id:Int):KnownFor{
        return KnownFor(
                0.0, 0, id, true,
                "", "Title " + id, 0.0, null,
                "", "", arrayListOf(),
                "", false, "", ""
        )
    }


}