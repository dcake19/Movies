package com.example.android.movies.ui.movies.detailed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.app.Fragment
import android.support.v4.widget.DrawerLayout
import com.example.android.movies.R
import com.example.android.movies.di.App
//import com.example.android.movies.di.movies.detailed.DaggerMovieDetailedComponent
import com.example.android.movies.di.movies.detailed.MovieDetailedComponent
import com.example.android.movies.di.movies.detailed.MovieDetailedModule
import com.example.android.movies.ui.BaseNavigationActivity
import com.example.android.movies.ui.movies.CreditsType
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsFragment
import com.example.android.movies.ui.movies.detailed.info.MoviesInfoFragment
import kotlinx.android.synthetic.main.movie_details_activity.*
import kotlinx.android.synthetic.main.movie_details_appbar.*

class MovieDetailsActivity : BaseNavigationActivity() {

    lateinit var component: MovieDetailedComponent

    companion object {
        const val MOVIE_ID = "movie_id"
        const val MOVIE_TITLE = "movie_title"

        fun getIntent(context: Context, id:Int, title:String): Intent {
            val intent = Intent(context,MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_ID,id)
            intent.putExtra(MOVIE_TITLE,title)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val app : App = application as App
//        component = DaggerMovieDetailedComponent.builder()
//                .appComponent(app.component)
//                .movieDetailedModule(MovieDetailedModule())
//                .build()

        movies_details_toolbar.title = intent.getStringExtra(MOVIE_TITLE)

        setupTabs()
        setFragment(0)
    }

    fun setupTabs(){
        tabs.addTab(tabs.newTab().setText(getString(R.string.title_details)),true)
        tabs.addTab(tabs.newTab().setText(getString(R.string.title_cast)))
        tabs.addTab(tabs.newTab().setText(getString(R.string.title_crew)))

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
       // fragmentManager.fragments
        //for (f in fragmentManager.fragments) ft.hide(f)
        for(i in 0..2){
            if (fragmentManager.findFragmentByTag(i.toString())!=null)
                ft.hide(fragmentManager.findFragmentByTag(i.toString()))
        }

        val fragment:Fragment

        if (fragmentManager.findFragmentByTag(position.toString())!=null){
            fragment = fragmentManager.findFragmentByTag(position.toString())
            ft.show(fragment).commit()
        }else {
            val bundle = Bundle()
            bundle.putInt(MOVIE_ID,intent.getIntExtra(MOVIE_ID,0))

            when (position) {
                0 -> fragment = MoviesInfoFragment()
                1 -> {
                    fragment = MovieCreditsFragment()
                    bundle.putInt(MovieCreditsFragment.CREDITS_TYPE, CreditsType.CAST)
                }
                2 -> {
                    fragment = MovieCreditsFragment()
                    bundle.putInt(MovieCreditsFragment.CREDITS_TYPE, CreditsType.CREW)
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
        return R.layout.movie_details_activity
    }

    override fun getViewId(): Int {
        return R.id.navigation_film_home
    }

    override fun getDrawerLayout(): DrawerLayout {
        return movie_details_drawer_layout
    }

}
