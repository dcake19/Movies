package com.example.android.movies.ui.movies.list.search

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.widget.SearchView
import com.example.android.movies.R
import com.example.android.movies.ui.NavigationIconActivity
import com.example.android.movies.ui.movies.MoviesDownloadTypes
import com.example.android.movies.ui.movies.list.MoviesListActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import kotlinx.android.synthetic.main.movies_search_activity.*
import kotlinx.android.synthetic.main.movies_search_appbar.*
import javax.inject.Inject

class MoviesSearchActivity: NavigationIconActivity(), HasFragmentInjector {

    companion object {
        const val SEARCH_QUERY = "search_query"

        fun getIntent(context: Context,query:String): Intent {
            val intent = Intent(context, MoviesSearchActivity::class.java)
            intent.putExtra(SEARCH_QUERY,query)
            return intent
        }
    }

    private val OUTSTATE_SEARCH_TERM = "search_term"

    @Inject
    lateinit var fragmentDispatchingAndroidInjector:
            DispatchingAndroidInjector<Fragment>

   // lateinit var fragment: BaseMoviesListFragment
   // lateinit var fragment: SearchFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null)
            setFragment(intent.getStringExtra(SEARCH_QUERY))
        else
            loadState(savedInstanceState)

        search.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val fragment = fragmentManager.findFragmentByTag(SearchFragment::class.java.name)
                        as SearchFragment
                fragment.presenter.search(query?:"")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun setFragment(query: String){
       //fragment = BaseMoviesListFragment()
        val fragment = SearchFragment()
        val bundle = Bundle()
        bundle.putInt(MoviesListActivity.DOWNLOAD_TYPE_KEY,MoviesDownloadTypes.SEARCH)
        bundle.putString(SEARCH_QUERY,query)
        fragment.arguments = bundle
        val ft = fragmentManager.beginTransaction()
//        ft.setCustomAnimations(
//                R.anim.abc_fade_in, R.anim.abc_fade_out)
//        ft.replace(R.id.search_content,fragment, BaseMoviesListFragment::class.java.name)
        ft.replace(R.id.search_content,fragment, SearchFragment::class.java.name)
        ft.commit()
    }

    private fun loadState(savedInstanceState: Bundle){
        search.setQuery(savedInstanceState.getString(OUTSTATE_SEARCH_TERM)?:"",false)
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        saveState(outState?:Bundle())
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        saveState(outState?:Bundle())
    }

    private fun saveState(outState: Bundle){
        outState.putString(OUTSTATE_SEARCH_TERM,search.query.toString())
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

    override fun fragmentInjector(): AndroidInjector<Fragment>
            = fragmentDispatchingAndroidInjector
}