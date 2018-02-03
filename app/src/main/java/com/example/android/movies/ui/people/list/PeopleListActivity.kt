package com.example.android.movies.ui.people.list

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.widget.SearchView
import com.example.android.movies.R
import com.example.android.movies.ui.NavigationIconActivity
import kotlinx.android.synthetic.main.people_list_activity.*
import kotlinx.android.synthetic.main.people_list_appbar.*

class PeopleListActivity : NavigationIconActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null)
            setFragment()

        search.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(fragmentManager.findFragmentByTag(PeopleListFragment::class.java.canonicalName)!=null){
                    val fragment = fragmentManager
                            .findFragmentByTag(PeopleListFragment::class.java.canonicalName)
                            as PeopleListFragment
                    fragment.presenter.searchPeople(query ?: "")
                }
//                if(fragmentManager.fragments.size>0) {
//                    val fragment = fragmentManager.fragments.get(0) as PeopleListFragment
//                    fragment.presenter.searchPeople(query ?: "")
//                }
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
                PeopleListFragment::class.java.canonicalName)
        ft.commit()
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
}
