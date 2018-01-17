package com.example.android.movies.ui.movies.detailed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import com.example.android.movies.R
import com.example.android.movies.ui.BaseNavigationActivity
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsFragment
import com.example.android.movies.ui.movies.detailed.info.MoviesInfoFragment
import kotlinx.android.synthetic.main.movie_details_activity.*
import kotlinx.android.synthetic.main.movie_details_appbar.*

class MovieDetailsActivity : BaseNavigationActivity() {

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
        setContentView(R.layout.movie_details_activity)

        movies_details_toolbar.title = intent.getStringExtra(MOVIE_TITLE)

        setupTabs()
        setFragment(0)
    }

//    fun setupTabs(){
//        val bundle = Bundle()
//        bundle.putInt(MOVIE_ID,intent.getIntExtra(MOVIE_ID,0))
//        tab_host.setup(this, getSupportFragmentManager(), android.R.id.tabcontent)
//        tab_host.addTab(tab_host.newTabSpec("tab1")
//                .setIndicator("tab 1",null),
//                MoviesInfoFragment::class.java,bundle)
//        tab_host.addTab(tab_host.newTabSpec("tab2")
//                .setIndicator("tab 2",null),
//                MovieCreditsFragment::class.java,bundle)
//    }

    fun setupTabs(){
        tabs.addTab(tabs.newTab().setText("one"),true)
        tabs.addTab(tabs.newTab().setText("two"))

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
        val fragment:Fragment

        when(position){
            0 -> fragment = MoviesInfoFragment()
            1 -> fragment = MovieCreditsFragment()
            else -> {return}
        }

        val bundle = Bundle()
        bundle.putInt(MOVIE_ID,intent.getIntExtra(MOVIE_ID,0))

        fragment.arguments = bundle

        val ft = supportFragmentManager.beginTransaction()
        ft.setCustomAnimations(
                R.anim.abc_fade_in, R.anim.abc_fade_out)
        ft.replace(R.id.frame_container,fragment)
        ft.commit()

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
