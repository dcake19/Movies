package com.example.android.movies.ui.movies.home

import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import com.example.android.movies.R
import com.example.android.movies.ui.NavigationIconActivity
import com.example.android.movies.ui.movies.MoviesDownloadTypes
import kotlinx.android.synthetic.main.movies_home_activity.*
import kotlinx.android.synthetic.main.movies_home_appbar.*

class MoviesHomeActivity : NavigationIconActivity() {

    companion object {
        const val DOWNLOAD_TYPE_KEY = "download_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movies_home_activity)

        if (savedInstanceState == null) {
            setFragment(MoviesDownloadTypes.NOW_PLAYING)
            setFragment(MoviesDownloadTypes.UPCOMING)
            setFragment(MoviesDownloadTypes.TOP_RATED)
        }
    }

    private fun setFragment(type:Int){
        val fragment = MoviesHomeFragment()
        val bundle = Bundle()
        bundle.putInt(DOWNLOAD_TYPE_KEY,type)
        fragment.arguments = bundle
        val ft = supportFragmentManager.beginTransaction()
        ft.setCustomAnimations(
                R.anim.abc_fade_in, R.anim.abc_fade_out)
        ft.replace(getContent(type),fragment)
        ft.commit()
    }

    private fun getContent(type:Int): Int{
        when(type){
            MoviesDownloadTypes.NOW_PLAYING -> return R.id.now_playing_content
            MoviesDownloadTypes.UPCOMING -> return R.id.upcoming_content
            MoviesDownloadTypes.TOP_RATED -> return R.id.top_rated_content
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
}
