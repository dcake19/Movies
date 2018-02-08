package com.example.android.movies.presenters

import android.content.Context
import com.example.android.movies.BuildConfig
import com.example.android.movies.R
import com.example.android.movies.RxSchedulerProvider
import com.example.android.movies.api.MoviesApi
import com.example.android.movies.api.data.people.PersonInfo
import com.example.android.movies.ui.people.detailed.info.PeopleInfoContract
import com.example.android.movies.ui.people.detailed.info.PeopleInfoInteractor
import com.example.android.movies.ui.people.detailed.info.PeopleInfoPresenter
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


class PeopleInfoPresenterTests {

    @Mock
    internal var view : PeopleInfoContract.View? = null

    @Mock
    internal var context: Context? = null

    @Mock
    val api: MoviesApi? = null

    lateinit var presenter: PeopleInfoPresenter

    val personInfo = PersonInfo("1980-03-16",null,1,"name",
            arrayListOf(),0,"biography",5.5,
            "London, UK","profilePath",false,
            "imdb1","homepage")

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
        Mockito.`when`(api!!.getPersonInfo("1", BuildConfig.TMDB_API_KEY))
                .thenReturn(getObservable())

        presenter = PeopleInfoPresenter(PeopleInfoInteractor(api!!),rxSchedulerProvider)
        presenter.addView(view!!)
    }

    @Test
    fun download(){
        presenter.downloadPersonInfo(1)
        verify(view)!!.display(personInfo.biography,
                "profilePath","Born: March 16, 1980 in London, UK")
    }

    private fun mockMethods(){
        Mockito.`when`(view!!.getContext()).thenReturn(context)
        Mockito.`when`(context!!.getString(R.string.born)).thenReturn("Born:")
        Mockito.`when`(context!!.getString(R.string._in)).thenReturn("in")
    }

    private fun getObservable():Observable<PersonInfo>{
        return Observable.create(object : ObservableOnSubscribe<PersonInfo> {
            override fun subscribe(e: ObservableEmitter<PersonInfo>?) {
                e!!.onNext(personInfo)
            }
        })
    }


}