package com.example.android.movies.ui.movies.list.discover

import android.app.Fragment
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.widget.ArrayAdapter
import com.example.android.movies.R
import com.example.android.movies.ui.NavigationIconActivity
import com.example.android.movies.ui.movies.DiscoverQuery
import com.example.android.movies.ui.movies.MoviesDownloadTypes
import com.example.android.movies.ui.movies.list.MoviesListActivity
import com.example.android.movies.ui.movies.list.BaseMoviesListFragment
import com.example.android.movies.util.DateUtil
import com.example.android.movies.util.GenreUtil
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import kotlinx.android.synthetic.main.movies_discover_activity.*
import kotlinx.android.synthetic.main.movies_discover_appbar.*
import kotlinx.android.synthetic.main.movies_discover_content.*
import kotlinx.android.synthetic.main.movies_discover_filters.*
import javax.inject.Inject

class MoviesDiscoverActivity : NavigationIconActivity(),DiscoverGenres , HasFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector:
            DispatchingAndroidInjector<Fragment>

    var includeGenres = BooleanArray(19)
    var excludeGenres = BooleanArray(19)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setFragment()

        setSortbySpinner()
        setYearsSpinners()

        btn_find.setOnClickListener {
            if(fragmentManager.findFragmentByTag(DiscoverFragment::class.java.canonicalName)!=null) {
                val fragment = fragmentManager
                        .findFragmentByTag(DiscoverFragment::class.java.canonicalName)
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
//            if(fragmentManager.findFragmentByTag(BaseMoviesListFragment::class.java.canonicalName)!=null) {
//                val fragment = fragmentManager
//                        .findFragmentByTag(BaseMoviesListFragment::class.java.canonicalName)
//                        as BaseMoviesListFragment
//                fragment.presenter.downloadDiscoverData(
//                        DiscoverQuery(getSortByString(spinner_sort_by.selectedItemPosition),
//                                GenreUtil.getGenresIdList(includeGenres),
//                                GenreUtil.getGenresIdList(excludeGenres),
//                                edit_text_vote_average_min.text.toString(),
//                                edit_text_vote_count_min.text.toString(),
//                                spinner_release_year.selectedItem.toString(),
//                                edit_text_runtime_min.text.toString(),
//                                edit_text_runtime_max.text.toString()))
//            }

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

    }

    private fun setFragment(){
        //val fragment = BaseMoviesListFragment()
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
