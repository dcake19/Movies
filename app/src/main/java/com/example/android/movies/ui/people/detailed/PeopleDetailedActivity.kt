package com.example.android.movies.ui.people.detailed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.app.Fragment
import android.support.v4.widget.DrawerLayout
import com.example.android.movies.R
//import com.example.android.movies.di.people.detailed.DaggerPeopleDetailedComponent
import com.example.android.movies.ui.BaseNavigationActivity
import com.example.android.movies.ui.movies.CreditsType
import com.example.android.movies.ui.people.detailed.credits.PeopleCreditsFragment
import com.example.android.movies.ui.people.detailed.info.PeopleInfoFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import kotlinx.android.synthetic.main.people_detailed_activity.*
import kotlinx.android.synthetic.main.people_detailed_appbar.*
import javax.inject.Inject

class PeopleDetailedActivity : BaseNavigationActivity()  , HasFragmentInjector {

    //lateinit var component: PeopleDetailedComponent

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

    @Inject
    lateinit var fragmentDispatchingAndroidInjector:
            DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        people_details_toolbar.title = intent.getStringExtra(PERSON_NAME)

        setupTabs()
        setFragment(0)
    }

    fun setupTabs(){
        tabs.addTab(tabs.newTab().setText(getString(R.string.title_bio)),true)
        tabs.addTab(tabs.newTab().setText(getString(R.string.title_cast)))
        tabs.addTab(tabs.newTab().setText(getString(R.string.title_crew)))
        tabs.setSelectedTabIndicatorHeight((4*resources.displayMetrics.density).toInt())
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
        val ft = fragmentManager.beginTransaction()

//        ft.setCustomAnimations(
//                R.anim.abc_fade_in, R.anim.abc_fade_out)

       // for (f in fragmentManager.fragments) ft.hide(f)
        for(i in 0..2){
            if (fragmentManager.findFragmentByTag(i.toString())!=null)
                ft.hide(fragmentManager.findFragmentByTag(i.toString()))
        }

        val fragment: Fragment

        if (fragmentManager.findFragmentByTag(position.toString())!=null){
            fragment = fragmentManager.findFragmentByTag(position.toString())
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

    override fun onBackPressed() {
        finish()
      //  super.onBackPressed()
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


    override fun fragmentInjector(): AndroidInjector<Fragment>
            = fragmentDispatchingAndroidInjector

}
