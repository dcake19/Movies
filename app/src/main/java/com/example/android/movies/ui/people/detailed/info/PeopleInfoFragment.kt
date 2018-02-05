package com.example.android.movies.ui.people.detailed.info

import android.os.Bundle
import android.app.Fragment
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.movies.R
//import com.example.android.movies.di.people.detailed.info.DaggerPeopleInfoComponent
import com.example.android.movies.loadImage
import com.example.android.movies.ui.people.detailed.PeopleDetailedActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.people_detailed_info_fragment.*
import javax.inject.Inject

class PeopleInfoFragment: Fragment(), PeopleInfoContract.View{

    @Inject lateinit var presenter: PeopleInfoContract.Presenter

    override fun onAttach(context: Context?) {
        AndroidInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

//        val app : App = activity.application as App
//        val component = DaggerPeopleInfoComponent.builder()
//                .appComponent(app.component)
//                .peopleInfoModule(PeopleInfoModule(this))
//                .build()
//        component.inject(this)
        presenter.addView(this)
        return inflater!!.inflate(R.layout.people_detailed_info_fragment,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        presenter.downloadPersonInfo(arguments.getInt(PeopleDetailedActivity.PERSON_ID))
        super.onViewCreated(view, savedInstanceState)
    }

    override fun display(biography: String, posterPath: String,born:String) {
        text_people_biography.text = biography
        image_poster.loadImage(getString(R.string.image_start_url),posterPath)
        text_born.text = born
    }

}
