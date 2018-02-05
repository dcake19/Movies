package com.example.android.movies.ui.people.list

import android.util.Log
import com.example.android.movies.BuildConfig
import com.example.android.movies.R
import com.example.android.movies.RxSchedulerProvider
import com.example.android.movies.api.data.people.PersonResults
import com.example.android.movies.util.TextUtil
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PeopleListPresenter @Inject constructor(
        val interactor:PeopleListInteractor,
        val rxSchedulerProvider: RxSchedulerProvider)
                         // val view:PeopleListContract.View)
    : PeopleListContract.Presenter {

    lateinit var view:PeopleListContract.View
    private lateinit var personResults:PersonResults
    private var query = ""

    override fun addView(view: PeopleListContract.View) {
        this.view = view
    }

    override fun downloadPopularPeople() {
       downloadPopularPeople(1)
    }

    private fun downloadPopularPeople(page:Int) {
       download(page,interactor.getPopularPeople(BuildConfig.TMDB_API_KEY,page.toString()))
    }

    override fun downloadPeopleNextPage() {
        if (personResults.page < personResults.totalPages) {
            if (query.equals(""))
                downloadPopularPeople(personResults.page + 1)
            else
                searchPeople(personResults.page + 1)
        }
    }

    override fun searchPeople(query: String) {
        this.query = query
        searchPeople(1)
    }

    private fun searchPeople(page:Int){
        download(page,interactor.getPersonSearchResults(
                BuildConfig.TMDB_API_KEY, query,page.toString()))
    }

    private fun download(page:Int,observable: Observable<PersonResults>){
        observable.subscribeOn(rxSchedulerProvider.subscribeOn())
                .observeOn(rxSchedulerProvider.observeOn())
                .subscribe(object : Observer<PersonResults> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(pr: PersonResults) {
                        if(page==1)personResults = pr
                        else personResults = PersonResults(pr.page,
                                pr.totalResults,pr.totalPages,
                                personResults.results + pr.results)

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
        //Log.v("PeopleListPresenter","PersonName: " + personResults.results.get(position).name?:"")
        return personResults.results.get(position).name?:""
    }

    override fun getPersonKnownFor(position: Int): String {
        return TextUtil.convertToCommaSeparatedString(
                view.getContext().getString(R.string.known_for),
                personResults.results[position].knownFor,{it->it?.title?:""})
    }
}