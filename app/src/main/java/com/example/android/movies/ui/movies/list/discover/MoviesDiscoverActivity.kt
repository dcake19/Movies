package com.example.android.movies.ui.movies.list.discover

import android.app.Fragment
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.widget.ArrayAdapter
import android.widget.EditText
import com.example.android.movies.R
import com.example.android.movies.ui.NavigationIconActivity
import com.example.android.movies.ui.movies.MoviesDownloadTypes
import com.example.android.movies.ui.movies.list.MoviesListActivity
import com.example.android.movies.util.DateUtil
import com.example.android.movies.util.GenreUtil
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import kotlinx.android.synthetic.main.movie_details_appbar.*
import kotlinx.android.synthetic.main.movies_discover_activity.*
import kotlinx.android.synthetic.main.movies_discover_appbar.*
import kotlinx.android.synthetic.main.movies_discover_content.*
import kotlinx.android.synthetic.main.movies_discover_filters.*
import javax.inject.Inject

class MoviesDiscoverActivity : NavigationIconActivity(),DiscoverGenres , HasFragmentInjector {

    private val OUTSTATE_FILTERS_UP = "filters_up"
    private val OUTSTATE_SORT_BY = "sort_by"
    private val OUTSTATE_INCLUDE_GENRES = "include_genres"
    private val OUTSTATE_EXCLUDE_GENRES = "exclude_genres"
    private val OUTSTATE_VOTE_AVERAGE = "vote_average"
    private val OUTSTATE_VOTE_COUNT = "vote_count"
    private val OUTSTATE_RELEASE_YEAR = "release_year"
    private val OUTSTATE_RUNTIME_MIN = "runtime_min"
    private val OUTSTATE_RUNTIME_MAX = "runtime_max"

    @Inject
    lateinit var fragmentDispatchingAndroidInjector:
            DispatchingAndroidInjector<Fragment>

    var includeGenres = BooleanArray(19)
    var excludeGenres = BooleanArray(19)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setSortbySpinner()
        setYearsSpinners()

        btn_find.setOnClickListener {
            if(fragmentManager.findFragmentByTag(DiscoverFragment::class.java.name)!=null) {
                val fragment = fragmentManager
                        .findFragmentByTag(DiscoverFragment::class.java.name)
                        as DiscoverFragment
                fragment.presenter.downloadDiscoverData(
                        DiscoverQuery(getSortByString(spinner_sort_by.selectedItemPosition),
                                GenreUtil.getGenresIdList(includeGenres),
                                GenreUtil.getGenresIdList(excludeGenres),
                                edit_text_vote_average_min.text.toString(),
                                edit_text_vote_count_min.text.toString(),
                                spinner_release_year.selectedItem.toString(),
                                edit_text_runtime_min.text.toString(),
                                edit_text_runtime_max.text.toString()))
            }

        }

        btn_expand.setOnClickListener {
            expandable_layout.toggle()
            if(expandable_layout.height==0)
                btn_expand.setImageResource(R.drawable.ic_expand_less_white_24dp)
            else
                btn_expand.setImageResource(R.drawable.ic_expand_more_white_24dp)
        }

        layout_include_genres.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean(GenresDialog.INCLUDE,true)
            bundle.putBooleanArray(GenresDialog.INITIAL,includeGenres)
            val genresDialog = GenresDialog()
            genresDialog.arguments = bundle
            genresDialog.show(supportFragmentManager,"dialog_include_genres")
        }

        layout_exclude_genres.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean(GenresDialog.INCLUDE,false)
            bundle.putBooleanArray(GenresDialog.INITIAL,excludeGenres)
            val genresDialog = GenresDialog()
            genresDialog.arguments = bundle
            genresDialog.show(supportFragmentManager,"dialog_exclude_genres")
        }

        if (savedInstanceState == null)
            setFragment()
        else
            loadState(savedInstanceState)

    }


    private fun setFragment(){
        val fragment = DiscoverFragment()
        val bundle = Bundle()
        bundle.putInt(MoviesListActivity.DOWNLOAD_TYPE_KEY, MoviesDownloadTypes.DISCOVER)
        fragment.arguments = bundle
        val ft = fragmentManager.beginTransaction()
//        ft.setCustomAnimations(
//                R.anim.abc_fade_in, R.anim.abc_fade_out)
//        ft.replace(R.id.movies_discover_content,fragment,
//        BaseMoviesListFragment::class.java.name)
        ft.replace(R.id.movies_discover_content,fragment, DiscoverFragment::class.java.name)
        ft.commit()
    }

    private fun setSortbySpinner(){
        val adapterSortBy = ArrayAdapter.createFromResource(this,
                R.array.sort_by_options, R.layout.movies_discover_spinner_item)
        adapterSortBy.setDropDownViewResource(R.layout.movies_discover_spinner_item)
        spinner_sort_by.setAdapter(adapterSortBy)
    }

    private fun setYearsSpinners(){
        val years = DateUtil.getYearsArray()
        val yearsAdapter = ArrayAdapter<String>(this,R.layout.movies_discover_spinner_item,years)
        spinner_release_year.adapter = yearsAdapter
    }

    private fun getSortByString(index:Int):String{
        val sortByOptions = resources.getStringArray(R.array.sort_by_options_request)
        return sortByOptions[index]
    }

    override fun setGenres(genres: BooleanArray, include: Boolean) {
        if(include) {
            includeGenres = genres
            text_include_genres.text = GenreUtil.getGenreList(includeGenres,this)
        }
        else {
            excludeGenres = genres
            text_exclude_genres.text = GenreUtil.getGenreList(excludeGenres, this)
        }
    }


    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        saveState(outState!!)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        saveState(outState!!)
    }

    private fun loadState(savedInstanceState: Bundle){
        if(!savedInstanceState.getBoolean(OUTSTATE_FILTERS_UP))
            btn_expand.setImageResource(R.drawable.ic_expand_less_white_24dp)
        else
            btn_expand.setImageResource(R.drawable.ic_expand_more_white_24dp)

        spinner_sort_by.setSelection(savedInstanceState.getInt(OUTSTATE_SORT_BY))
        setGenres(savedInstanceState.getBooleanArray(OUTSTATE_INCLUDE_GENRES),true)
        setGenres(savedInstanceState.getBooleanArray(OUTSTATE_EXCLUDE_GENRES),false)
        edit_text_vote_average_min.setText(savedInstanceState.getString(OUTSTATE_VOTE_AVERAGE)?:"")
        edit_text_vote_count_min.setText(savedInstanceState.getString(OUTSTATE_VOTE_COUNT)?:"")
        spinner_release_year.setSelection(savedInstanceState.getInt(OUTSTATE_RELEASE_YEAR))
        edit_text_runtime_min.setText(savedInstanceState.getString(OUTSTATE_RUNTIME_MIN)?:"")
        edit_text_runtime_max.setText(savedInstanceState.getString(OUTSTATE_RUNTIME_MAX)?:"")
    }

    private fun saveState(outState: Bundle){
        outState.putBoolean(OUTSTATE_FILTERS_UP,expandable_layout.height==0)
        outState.putInt(OUTSTATE_SORT_BY,spinner_sort_by.selectedItemPosition)
        outState.putBooleanArray(OUTSTATE_INCLUDE_GENRES,includeGenres)
        outState.putBooleanArray(OUTSTATE_EXCLUDE_GENRES,excludeGenres)
        outState.putString(OUTSTATE_VOTE_AVERAGE,edit_text_vote_average_min.text.toString())
        outState.putString(OUTSTATE_VOTE_COUNT,edit_text_vote_count_min.text.toString())
        outState.putInt(OUTSTATE_RELEASE_YEAR,spinner_release_year.selectedItemPosition)
        outState.putString(OUTSTATE_RUNTIME_MIN,edit_text_runtime_min.text.toString())
        outState.putString(OUTSTATE_RUNTIME_MAX,edit_text_runtime_max.text.toString())
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

    override fun fragmentInjector(): AndroidInjector<Fragment>
            = fragmentDispatchingAndroidInjector

}
