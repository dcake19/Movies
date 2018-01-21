package com.example.android.movies.ui

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.android.movies.R
import com.example.android.movies.ui.movies.MoviesDownloadTypes
import com.example.android.movies.ui.movies.home.MoviesHomeActivity
import com.example.android.movies.ui.movies.list.MoviesListActivity
import com.example.android.movies.ui.people.list.PeopleListActivity
import kotlinx.android.synthetic.main.navigation_drawer.*


abstract class BaseNavigationActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResourceId())

        navigation_film_home.setOnClickListener {
            clickNavigationDrawer(R.id.navigation_film_home)
        }

        navigation_now_playing.setOnClickListener {
            clickNavigationDrawer(R.id.navigation_now_playing)
        }
        navigation_upcoming.setOnClickListener {
            clickNavigationDrawer(R.id.navigation_upcoming)
        }
        navigation_top_rated.setOnClickListener {
            clickNavigationDrawer(R.id.navigation_top_rated)
        }
        navigation_popular.setOnClickListener {
            clickNavigationDrawer(R.id.navigation_popular)
        }
        navigation_people_list.setOnClickListener {
            clickNavigationDrawer(R.id.navigation_people_list)
        }

        navigation_drawer.setPadding(0, getStatusBarHeight(), 0, 0)
    }

    //the layout of the sub class
    protected abstract fun getLayoutResourceId(): Int

    // the id of the view relating to the activity currently being displayed
    protected abstract fun getViewId(): Int

    // the drawer layout in the activity
    protected abstract fun getDrawerLayout(): DrawerLayout

    fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    private fun clickNavigationDrawer(id:Int){
        if(id == getViewId())
            getDrawerLayout().closeDrawer(navigation_drawer)
        else
            startNewActivity(id)
    }

    fun startNewActivity(id:Int){
        when(id){
            R.id.navigation_film_home ->
                startActivity(Intent(this,MoviesHomeActivity::class.java))
            R.id.navigation_now_playing ->
                startActivity(MoviesListActivity.getIntent(this,MoviesDownloadTypes.NOW_PLAYING))
            R.id.navigation_upcoming ->
                startActivity(MoviesListActivity.getIntent(this,MoviesDownloadTypes.UPCOMING))
            R.id.navigation_top_rated ->
                startActivity(MoviesListActivity.getIntent(this,MoviesDownloadTypes.TOP_RATED))
            R.id.navigation_popular ->
                startActivity(MoviesListActivity.getIntent(this,MoviesDownloadTypes.POPULAR))
            R.id.navigation_people_list ->
                startActivity(Intent(this,PeopleListActivity::class.java))
        }
        getDrawerLayout().closeDrawer(navigation_drawer)
    }

}


