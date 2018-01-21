package com.example.android.movies.ui.people.list

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.movies.R
import com.example.android.movies.di.App
import com.example.android.movies.di.people.list.DaggerPeopleListComponent
import com.example.android.movies.di.people.list.PeopleListModule
import kotlinx.android.synthetic.main.people_list_fragment.*
import javax.inject.Inject


class PeopleListFragment: Fragment(), PeopleListContract.View {

    @Inject lateinit var presenter: PeopleListContract.Presenter
    @Inject lateinit var adapter: PeopleListAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val app : App = activity.application as App
        DaggerPeopleListComponent.builder()
                .appComponent(app.component)
                .peopleListModule(PeopleListModule(this))
                .build().inject(this)

        return inflater!!.inflate(R.layout.people_list_fragment,container,false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        presenter.downloadPopularPeople()

        super.onViewCreated(view, savedInstanceState)

        recycler_people.apply {
            val linearLayout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            layoutManager = linearLayout
        }

        if (recycler_people.adapter == null)
            recycler_people.adapter = adapter

    }

    override fun display(size: Int) {
        adapter.display(size)
    }
}