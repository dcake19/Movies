package com.example.android.movies.ui.movies.list.discover

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import com.example.android.movies.R
import com.example.android.movies.ui.NavigationIconActivity
import com.example.android.movies.ui.movies.DiscoverQuery
import com.example.android.movies.ui.movies.MoviesDownloadTypes
import com.example.android.movies.ui.movies.list.MoviesListActivity
import com.example.android.movies.ui.movies.list.MoviesListFragment
import com.example.android.movies.ui.movies.list.search.MoviesSearchActivity
import kotlinx.android.synthetic.main.movies_discover_activity.*
import kotlinx.android.synthetic.main.movies_discover_appbar.*

class MoviesDiscoverActivity : NavigationIconActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragment()

        btn_find.setOnClickListener {
            if(supportFragmentManager.fragments.size>0) run {
                val fragment = supportFragmentManager.fragments.get(0) as MoviesListFragment
                fragment.presenter.downloadDiscoverData(DiscoverQuery())
            }
        }
    }



    private fun setFragment(){
        val fragment = MoviesListFragment()
        val bundle = Bundle()
        bundle.putInt(MoviesListActivity.DOWNLOAD_TYPE_KEY, MoviesDownloadTypes.DISCOVER)
        fragment.arguments = bundle
        val ft = supportFragmentManager.beginTransaction()
        ft.setCustomAnimations(
                R.anim.abc_fade_in, R.anim.abc_fade_out)
        ft.replace(R.id.movies_discover_content,fragment)
        ft.commit()
    }


    override fun getToolbar(): Toolbar {
        return movies_discover_toolbar
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.movies_discover_activity
    }

    override fun getViewId(): Int {
        return R.id.navigation_discover
    }

    override fun getDrawerLayout(): DrawerLayout {
        return movies_discover_drawer_layout
    }


}
