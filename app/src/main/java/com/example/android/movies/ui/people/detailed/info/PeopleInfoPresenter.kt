package com.example.android.movies.ui.people.detailed.info

import android.view.View
import com.example.android.movies.BuildConfig
import com.example.android.movies.R
import com.example.android.movies.RxSchedulerProvider
import com.example.android.movies.api.data.people.PersonInfo
import com.example.android.movies.util.DateUtil
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class PeopleInfoPresenter @Inject constructor(val interactor: PeopleInfoInteractor,
                                             val rxSchedulerProvider: RxSchedulerProvider)
                                             //val view: PeopleInfoContract.View)
    : PeopleInfoContract.Presenter {

    private lateinit var view: PeopleInfoContract.View
    private lateinit var personInfo:PersonInfo
    private var subscribed = false
    private var complete = false

    override fun addView(view: PeopleInfoContract.View) {
        this.view = view
    }

    override fun downloadPersonInfo(id: Int) {
        if (complete)
            display()
        else if(!subscribed) {
            val observable = interactor.getPersonInfo(id.toString(), BuildConfig.TMDB_API_KEY)
            observable.subscribeOn(rxSchedulerProvider.subscribeOn())
                    .observeOn(rxSchedulerProvider.observeOn())
                    .subscribe(object : Observer<PersonInfo> {
                        override fun onNext(t: PersonInfo) {
                            personInfo = t
                            display()
                        }

                        override fun onError(e: Throwable?) {
                            subscribed = false
                        }

                        override fun onSubscribe(d: Disposable?) {
                            subscribed = true
                        }

                        override fun onComplete() {
                            complete = true
                        }
                    })
        }
    }

    private fun display(){
        view.display(personInfo.biography,
                personInfo.profilePath?:"",
                getBorn())
    }

    private fun getBorn():String{
        if (personInfo.birthday!=null && personInfo.placeOfBirth!=null)
            return view.getContext().getString(R.string.born) + " " +
                    DateUtil.convertDateFormate(personInfo.birthday?:"") + " " +
                    view.getContext().getString(R.string._in) + " " +
                    personInfo.placeOfBirth

        else return ""
    }

}