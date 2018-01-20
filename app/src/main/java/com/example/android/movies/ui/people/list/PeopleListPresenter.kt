package com.example.android.movies.ui.people.list

import com.example.android.movies.BuildConfig
import com.example.android.movies.api.data.people.PersonResults
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class PeopleListPresenter(val interactor:PeopleListInteractor,val view:PeopleListContract.View)
    : PeopleListContract.Presenter {

    private lateinit var personResults:PersonResults
    private var query = ""

    override fun downloadPopularPeople() {
       downloadPopularPeople(1)
    }

    private fun downloadPopularPeople(page:Int) {
       download(interactor.getPopularPeople(BuildConfig.TMDB_API_KEY,page.toString()))
    }

    override fun searchPeople(query: String) {
        this.query = query
        searchPeople(1)
    }

    private fun searchPeople(page:Int){
        download(interactor.getPersonSearchResults(
                BuildConfig.TMDB_API_KEY, query,page.toString()))
    }

    private fun download(observable: Observable<PersonResults>){
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<PersonResults> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(pr: PersonResults) {
                        personResults = pr
                        view.display(personResults.results.size)
                    }

                    override fun onError(e: Throwable) {
                    }

                    override fun onComplete() {
                    }
                })
    }

    override fun getPosterPath(position: Int): String {
        return personResults.results.get(position).profilePath?:""
    }

    override fun getPersonId(position: Int): Int {
        return personResults.results.get(position).id?:-1
    }

    override fun getPersonName(position: Int): String {
        return personResults.results.get(position).name?:""
    }
}