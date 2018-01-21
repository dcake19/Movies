package com.example.android.movies.ui.people.detailed

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import com.example.android.movies.R
import com.example.android.movies.di.App
import com.example.android.movies.di.people.detailed.DaggerPeopleDetailedComponent
import com.example.android.movies.di.people.detailed.PeopleDetailedComponent
import com.example.android.movies.di.people.detailed.PeopleDetailedModule
import com.example.android.movies.ui.BaseNavigationActivity
import com.example.android.movies.ui.movies.CreditsType
import com.example.android.movies.ui.people.detailed.credits.PeopleCreditsFragment
import com.example.android.movies.ui.people.detailed.info.PeopleInfoFragment

import kotlinx.android.synthetic.main.people_detailed_activity.*
import kotlinx.android.synthetic.main.people_detailed_appbar.*

class PeopleDetailedActivity : BaseNavigationActivity() {

    lateinit var component: PeopleDetailedComponent

    companion object {
        const val PERSON_ID = "person_id"
        const val PERSON_NAME = "person_name"

        fun getIntent(context: Context, id:Int, name:String): Intent {
            val intent = Intent(context,PeopleDetailedActivity::class.java)
            intent.putExtra(PERSON_ID,id)
            intent.putExtra(PERSON_NAME,name)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val app : App = application as App
        component = DaggerPeopleDetailedComponent.builder()
                .appComponent(app.component)
                .peopleDetailedModule(PeopleDetailedModule())
                .build()

        people_details_toolbar.title = intent.getStringExtra(PERSON_NAME)

        setupTabs()
        setFragment(0)
    }

    fun setupTabs(){
        tabs.addTab(tabs.newTab().setText("one"),true)
        tabs.addTab(tabs.newTab().setText("two"))
        tabs.addTab(tabs.newTab().setText("three"))

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                setFragment(tab.position)
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun setFragment(position:Int){
        val ft = supportFragmentManager.beginTransaction()

        ft.setCustomAnimations(
                R.anim.abc_fade_in, R.anim.abc_fade_out)

        for (f in supportFragmentManager.fragments) ft.hide(f)

        val fragment: Fragment

        if (supportFragmentManager.findFragmentByTag(position.toString())!=null){
            fragment = supportFragmentManager.findFragmentByTag(position.toString())
            ft.show(fragment).commit()
        }else {
            val bundle = Bundle()
            bundle.putInt(PERSON_ID,intent.getIntExtra(PERSON_ID,0))

            when (position) {
                0 -> fragment = PeopleInfoFragment()
                1 -> {
                    fragment = PeopleCreditsFragment()
                    bundle.putInt(PeopleCreditsFragment.CREDITS_TYPE, CreditsType.CAST)
                }
                2 -> {
                    fragment = PeopleCreditsFragment()
                    bundle.putInt(PeopleCreditsFragment.CREDITS_TYPE, CreditsType.CREW)
                }
                else -> {return}
            }
            fragment.arguments = bundle
            ft.add(R.id.frame_container,fragment,position.toString())
            ft.addToBackStack(position.toString())
            ft.commit()
        }
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.people_detailed_activity
    }

    override fun getViewId(): Int {
        return R.id.navigation_film_home
    }

    override fun getDrawerLayout(): DrawerLayout {
        return people_details_drawer_layout
    }
}
