package com.example.android.movies.ui.movies.home

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.widget.SearchView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import com.example.android.movies.R
import com.example.android.movies.ui.NavigationIconActivity
import com.example.android.movies.ui.movies.MoviesDownloadTypes
import com.example.android.movies.ui.movies.list.search.MoviesSearchActivity
import com.example.android.movies.ui.people.list.PeopleListActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import kotlinx.android.synthetic.main.movie_details_activity.*
import kotlinx.android.synthetic.main.movies_home_activity.*
import kotlinx.android.synthetic.main.movies_home_appbar.*
import kotlinx.android.synthetic.main.navigation_drawer.*
import javax.inject.Inject

class MoviesHomeActivity : NavigationIconActivity(), HasFragmentInjector {

    companion object {
        const val DOWNLOAD_TYPE_KEY = "download_key"
    }

    @Inject
    lateinit var fragmentDispatchingAndroidInjector:
            DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.movies_home_activity)

        if (savedInstanceState == null) {
            setFragment(MoviesDownloadTypes.NOW_PLAYING)
            setFragment(MoviesDownloadTypes.UPCOMING)
            setFragment(MoviesDownloadTypes.TOP_RATED)
            setFragment(MoviesDownloadTypes.POPULAR)
        }

        movies_home_drawer_layout.setOnFocusChangeListener(object : View.OnFocusChangeListener{
            override fun onFocusChange(p0: View?, p1: Boolean) {
                Log.v("drawer layout","Focus changed")
            }

        })
        Log.v("MoviesHomeActivity","is focusable: " + movies_home_drawer_layout.isFocusable.toString())

        search.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                startActivity(MoviesSearchActivity.getIntent(
                        this@MoviesHomeActivity,query?:""))
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun setFragment(type:Int){
        val fragment = MoviesHomeFragment()
        val bundle = Bundle()
        bundle.putInt(DOWNLOAD_TYPE_KEY,type)
        fragment.arguments = bundle
        val ft = fragmentManager.beginTransaction()
//        ft.setCustomAnimations(
//                R.anim.abc_fade_in, R.anim.abc_fade_out)
        ft.replace(getContent(type),fragment,
                MoviesHomeFragment::class.java.name + " " + type)
        ft.commit()
    }

    private fun getContent(type:Int): Int{
        when(type){
            MoviesDownloadTypes.NOW_PLAYING -> return R.id.now_playing_content
            MoviesDownloadTypes.UPCOMING -> return R.id.upcoming_content
            MoviesDownloadTypes.TOP_RATED -> return R.id.top_rated_content
            MoviesDownloadTypes.POPULAR -> return R.id.popular_content
            else->{return -1}
        }
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.movies_home_activity
    }

    override fun getViewId(): Int {
        return R.id.navigation_film_home
    }

    override fun getDrawerLayout(): DrawerLayout {
        return movies_home_drawer_layout
    }

    override fun getToolbar(): Toolbar {
        return movies_home_toolbar
    }

    override fun fragmentInjector(): AndroidInjector<Fragment>
            = fragmentDispatchingAndroidInjector
}
