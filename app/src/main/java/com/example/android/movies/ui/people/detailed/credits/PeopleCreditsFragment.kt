package com.example.android.movies.ui.people.detailed.credits

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.movies.R
import com.example.android.movies.di.people.detailed.credits.DaggerPeopleCreditsComponent
import com.example.android.movies.di.people.detailed.credits.PeopleCreditsModule
import com.example.android.movies.ui.people.detailed.PeopleDetailedActivity
import kotlinx.android.synthetic.main.people_detailed_cast_fragment.*
import javax.inject.Inject

class PeopleCreditsFragment: Fragment(), PeopleCreditsContract.View {


    companion object {
        const val CREDITS_TYPE = "credits_type"
    }

    @Inject lateinit var presenter: PeopleCreditsContract.Presenter
    @Inject lateinit var adapter: PeopleCreditsAdapter


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val peopleDetailed = activity as PeopleDetailedActivity
        DaggerPeopleCreditsComponent.builder()
                .peopleDetailedComponent(peopleDetailed.component)
                .peopleCreditsModule(PeopleCreditsModule(this,arguments.getInt(CREDITS_TYPE)))
                .build()
                .inject(this)

        retainInstance = true
        return inflater!!.inflate(R.layout.people_detailed_cast_fragment,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        presenter.downloadCredits(arguments.getInt(PeopleDetailedActivity.PERSON_ID))
        recycler_credits.apply {
            val linearLayout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            layoutManager = linearLayout
        }

        if (recycler_credits.adapter == null)
            recycler_credits.adapter = adapter
    }

    override fun display(sizeCast: Int, sizeCrew: Int) {
        adapter.display(sizeCast, sizeCrew)
    }

}