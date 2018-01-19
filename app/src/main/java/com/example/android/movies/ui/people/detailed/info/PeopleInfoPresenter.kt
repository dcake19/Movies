package com.example.android.movies.ui.people.detailed.info

import com.example.android.movies.BuildConfig
import com.example.android.movies.api.data.people.PersonInfo
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class PeopleInfoPresenter(val interactor: PeopleInfoInteractor,val view: PeopleInfoContract.View)
    : PeopleInfoContract.Presenter {

    private lateinit var personInfo:PersonInfo

    override fun downloadPersonInfo(id: Int) {

        val observable = interactor.getPersonInfo(id.toString(), BuildConfig.TMDB_API_KEY)
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<PersonInfo>{
                    override fun onNext(t: PersonInfo) {
                        personInfo = t
                        display()
                    }

                    override fun onComplete() {

                    }

                    override fun onError(e: Throwable?) {

                    }

                    override fun onSubscribe(d: Disposable?) {

                    }
                })
    }

    private fun display(){
        view.display(personInfo.biography?:"",personInfo.profilePath?:"")
    }

}