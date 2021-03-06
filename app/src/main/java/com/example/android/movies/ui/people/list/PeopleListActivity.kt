package com.example.android.movies.ui.people.list

import android.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.widget.SearchView
import com.example.android.movies.R
import com.example.android.movies.ui.NavigationIconActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import kotlinx.android.synthetic.main.people_list_activity.*
import kotlinx.android.synthetic.main.people_list_appbar.*
import javax.inject.Inject

class PeopleListActivity : NavigationIconActivity(), HasFragmentInjector {

    private val OUTSTATE_SEARCH_TERM = "search_term"

    @Inject
    lateinit var fragmentDispatchingAndroidInjector:
            DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null)
            setFragment()
        else
            loadState(savedInstanceState)

        search.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(fragmentManager.findFragmentByTag(PeopleListFragment::class.java.name)!=null){
                    val fragment = fragmentManager
                            .findFragmentByTag(PeopleListFragment::class.java.name)
                            as PeopleListFragment
                    fragment.presenter.searchPeople(query ?: "")
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun setFragment(){
        val ft = fragmentManager.beginTransaction()
//        ft.setCustomAnimations(
//                R.anim.abc_fade_in, R.anim.abc_fade_out)
        ft.replace(R.id.people_list_content,PeopleListFragment(),
                PeopleListFragment::class.java.name)
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
        return R.layout.people_list_activity
    }

    override fun getViewId(): Int {
        return R.id.navigation_people_list
    }

    override fun getToolbar(): Toolbar {
        return people_search_toolbar
    }

    override fun getDrawerLayout(): DrawerLayout {
        return people_list_drawer_layout
    }

    override fun fragmentInjector(): AndroidInjector<Fragment>
            = fragmentDispatchingAndroidInjector
}
