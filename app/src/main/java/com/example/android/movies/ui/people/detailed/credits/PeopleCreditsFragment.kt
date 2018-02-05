package com.example.android.movies.ui.people.detailed.credits

import android.os.Bundle
import android.app.Fragment
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.movies.R
import com.example.android.movies.di.ActivityScope

import com.example.android.movies.di.people.detailed.credits.PeopleCreditsModule
import com.example.android.movies.ui.people.detailed.PeopleDetailedActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.people_detailed_cast_fragment.*
import javax.inject.Inject

@ActivityScope
class PeopleCreditsFragment: Fragment(), PeopleCreditsContract.View {

    companion object {
        const val CREDITS_TYPE = "credits_type"
    }

    @Inject lateinit var presenter: PeopleCreditsContract.Presenter
   // @Inject
    lateinit var adapter: PeopleCreditsAdapter

    override fun onAttach(context: Context?) {
        AndroidInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

//        val peopleDetailed = activity as PeopleDetailedActivity
//        DaggerPeopleCreditsComponent.builder()
//                .peopleDetailedComponent(peopleDetailed.component)
//                .peopleCreditsModule(PeopleCreditsModule(this,arguments.getInt(CREDITS_TYPE)))
//                .build()
//                .inject(this)
        presenter.changeView(this)
        adapter = PeopleCreditsAdapter(presenter,arguments.getInt(CREDITS_TYPE))
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