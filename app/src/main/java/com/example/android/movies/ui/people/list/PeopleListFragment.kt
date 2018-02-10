package com.example.android.movies.ui.people.list

import android.os.Bundle
import android.app.Fragment
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.movies.R
//import com.example.android.movies.di.people.list.DaggerPeopleListComponent
import com.example.android.movies.ui.EndlessRecyclerViewScrollListener
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.people_list_fragment.*
import javax.inject.Inject


class PeopleListFragment: Fragment(), PeopleListContract.View {

    @Inject lateinit var presenter: PeopleListContract.Presenter
   // @Inject
    lateinit var adapter: PeopleListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        presenter.addView(this)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        adapter = PeopleListAdapter(presenter)
        return inflater!!.inflate(R.layout.people_list_fragment,container,false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        presenter.downloadPopularPeople()

        super.onViewCreated(view, savedInstanceState)

        recycler_people.apply {
            val linearLayout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            layoutManager = linearLayout
        }

        class EndlessListener(layout:LinearLayoutManager): EndlessRecyclerViewScrollListener(layout) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                presenter.downloadPeopleNextPage()
            }
        }

        recycler_people.addOnScrollListener(
                EndlessListener(recycler_people.layoutManager as LinearLayoutManager))

        if (recycler_people.adapter == null)
            recycler_people.adapter = adapter

    }

    override fun display(size: Int) {
        adapter.display(size)
        recycler_people.scrollToPosition(0)
    }
}