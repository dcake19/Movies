package com.example.android.movies.ui.movies.detailed

import android.content.Context
import android.content.Intent
import android.os.Bundle
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
    }

    fun setupTabs(){
        val bundle = Bundle()
        bundle.putInt(MOVIE_ID,intent.getIntExtra(MOVIE_ID,0))
        tab_host.setup(this, getSupportFragmentManager(), android.R.id.tabcontent)
        tab_host.addTab(tab_host.newTabSpec("tab1")
                .setIndicator("tab 1",null),
                MoviesInfoFragment::class.java,bundle)
        tab_host.addTab(tab_host.newTabSpec("tab2")
                .setIndicator("tab 2",null),
                MovieCreditsFragment::class.java,bundle)
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
