package com.example.android.movies.ui.movies.list.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.widget.SearchView
import com.example.android.movies.R
import com.example.android.movies.ui.NavigationIconActivity
import com.example.android.movies.ui.movies.MoviesDownloadTypes
import com.example.android.movies.ui.movies.list.MoviesListActivity
import com.example.android.movies.ui.movies.list.MoviesListFragment
import kotlinx.android.synthetic.main.movies_search_activity.*
import kotlinx.android.synthetic.main.movies_search_appbar.*

class MoviesSearchActivity: NavigationIconActivity() {

    companion object {
        const val SEARCH_QUERY = "search_query"

        fun getIntent(context: Context,query:String): Intent {
            val intent = Intent(context, MoviesSearchActivity::class.java)
            intent.putExtra(SEARCH_QUERY,query)
            return intent
        }
    }

    lateinit var fragment:MoviesListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragment(intent.getStringExtra(SEARCH_QUERY))

        search.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                fragment.presenter.search(query?:"")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun setFragment(query: String){
        fragment = MoviesListFragment()
        val bundle = Bundle()
        bundle.putInt(MoviesListActivity.DOWNLOAD_TYPE_KEY,MoviesDownloadTypes.SEARCH)
        bundle.putString(SEARCH_QUERY,query)
        fragment.arguments = bundle
        val ft = supportFragmentManager.beginTransaction()
        ft.setCustomAnimations(
                R.anim.abc_fade_in, R.anim.abc_fade_out)
        ft.replace(R.id.search_content,fragment)
        ft.commit()
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.movies_search_activity
    }

    override fun getViewId(): Int {
        return -1
    }

    override fun getToolbar(): Toolbar {
        return movies_search_toolbar
    }

    override fun getDrawerLayout(): DrawerLayout {
        return movies_search_drawer_layout
    }

}