package com.example.android.movies.ui.movies.detailed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.app.Fragment
import android.support.v4.widget.DrawerLayout
import com.example.android.movies.R
import com.example.android.movies.loadImage
//import com.example.android.movies.di.movies.detailed.DaggerMovieDetailedComponent
import com.example.android.movies.ui.BaseNavigationActivity
import com.example.android.movies.ui.movies.CreditsType
import com.example.android.movies.ui.movies.MoviesDownloadTypes
import com.example.android.movies.ui.movies.detailed.credits.MovieCreditsFragment
import com.example.android.movies.ui.movies.detailed.info.MoviesInfoFragment
import com.example.android.movies.ui.movies.list.MoviesListActivity
import com.example.android.movies.ui.movies.list.BaseMoviesListFragment
import com.example.android.movies.util.ColorUtil
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import kotlinx.android.synthetic.main.movie_details_activity.*
import kotlinx.android.synthetic.main.movie_details_appbar.*
//import kotlinx.android.synthetic.main.movie_details_appbar.*
//import kotlinx.android.synthetic.main.movie_details_appbar_display.*

import javax.inject.Inject

class MovieDetailsActivity : BaseNavigationActivity() , HasFragmentInjector {

   // lateinit var component: MovieDetailedComponent

    companion object {
        const val MOVIE_ID = "movie_id"
        const val MOVIE_TITLE = "movie_title"
        const val MOVIE_BACKGROUND_COLOR = "movie_background_color"
        const val MOVIE_INDICATOR_COLOR = "movie_indicator_color"
        const val MOVIE_BACKDROP_PATH = "movie_backdrop_path"

        fun getIntent(context: Context, id:Int, title:String,
                      backgroundColor:Int, indicatorColor:Int,
                      backdropPath:String): Intent {
            val intent = Intent(context,MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_ID,id)
            intent.putExtra(MOVIE_TITLE,title)
            intent.putExtra(MOVIE_BACKGROUND_COLOR,backgroundColor)
            intent.putExtra(MOVIE_INDICATOR_COLOR,indicatorColor)
            intent.putExtra(MOVIE_BACKDROP_PATH,backdropPath)
            return intent
        }
        fun getIntent(context: Context, id:Int, title:String): Intent {
            val intent = Intent(context,MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_ID,id)
            intent.putExtra(MOVIE_TITLE,title)
            return intent
        }

    }

    @Inject
    lateinit var fragmentDispatchingAndroidInjector:
            DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        //movies_details_toolbar.title = intent.getStringExtra(MOVIE_TITLE)
        image_backdrop.loadImage(getString(R.string.image_start_url780),intent.getStringExtra(MOVIE_BACKDROP_PATH))
        text_title.text = intent.getStringExtra(MOVIE_TITLE)
        //movies_details_toolbar.setBackgroundColor(intent.getIntExtra(MOVIE_BACKGROUND_COLOR,0))
        tabs.setBackgroundColor(intent.getIntExtra(MOVIE_BACKGROUND_COLOR,0))
        tabs.setSelectedTabIndicatorColor(intent.getIntExtra(MOVIE_INDICATOR_COLOR,0))
        nested_scroll.setBackgroundColor(ColorUtil.getLighterShade(intent.getIntExtra(MOVIE_BACKGROUND_COLOR,0)))
        setupTabs()
        setFragment(0)
    }

    fun setupTabs(){
        tabs.addTab(tabs.newTab().setText(getString(R.string.title_details)),true)
        tabs.addTab(tabs.newTab().setText(getString(R.string.title_cast)))
        tabs.addTab(tabs.newTab().setText(getString(R.string.title_crew)))
        tabs.addTab(tabs.newTab().setText(getString(R.string.title_recommendations)))
        tabs.addTab(tabs.newTab().setText(getString(R.string.title_similar)))
        tabs.setSelectedTabIndicatorHeight((4*resources.displayMetrics.density).toInt())
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                setFragment(tab.position)
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun setFragment(position:Int){
        val ft = fragmentManager.beginTransaction()
//        ft.setCustomAnimations(
//                R.anim.abc_fade_in, R.anim.abc_fade_out)
       // fragmentManager.fragments
        //for (f in fragmentManager.fragments) ft.hide(f)
        for(i in 0..4){
            if (fragmentManager.findFragmentByTag(i.toString())!=null)
                ft.hide(fragmentManager.findFragmentByTag(i.toString()))
        }

        val fragment:Fragment

        if (fragmentManager.findFragmentByTag(position.toString())!=null){
            fragment = fragmentManager.findFragmentByTag(position.toString())
            ft.show(fragment).commit()
        }else {
            val bundle = Bundle()
            bundle.putInt(MOVIE_ID,intent.getIntExtra(MOVIE_ID,0))

            when (position) {
                0 -> fragment = MoviesInfoFragment()
                1 -> {
                    fragment = MovieCreditsFragment()
                    bundle.putInt(MovieCreditsFragment.CREDITS_TYPE, CreditsType.CAST)
                    bundle.putInt(MOVIE_INDICATOR_COLOR,intent.getIntExtra(MOVIE_INDICATOR_COLOR,0))
                }
                2 -> {
                    fragment = MovieCreditsFragment()
                    bundle.putInt(MovieCreditsFragment.CREDITS_TYPE, CreditsType.CREW)
                    bundle.putInt(MOVIE_INDICATOR_COLOR,intent.getIntExtra(MOVIE_INDICATOR_COLOR,0))
                }
                3 ->{
                    //fragment = BaseMoviesListFragment()
                    fragment = MoviesRelatedFragment()
                    bundle.putInt(MoviesListActivity.DOWNLOAD_TYPE_KEY,MoviesDownloadTypes.RECOMMENDATIONS)
                    bundle.putInt(MOVIE_BACKGROUND_COLOR,intent.getIntExtra(MOVIE_BACKGROUND_COLOR,0))
                }
                4 ->{
                    //fragment = BaseMoviesListFragment()
                    fragment = MoviesRelatedFragment()
                    bundle.putInt(MoviesListActivity.DOWNLOAD_TYPE_KEY,MoviesDownloadTypes.SIMILAR)
                    bundle.putInt(MOVIE_BACKGROUND_COLOR,intent.getIntExtra(MOVIE_BACKGROUND_COLOR,0))
                }
                else -> {return}
            }
            fragment.arguments = bundle
            ft.add(R.id.frame_container,fragment,position.toString())
            ft.addToBackStack(position.toString())
            ft.commit()
        }
    }

    override fun onBackPressed() {
        finish()
       // super.onBackPressed()
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

    override fun fragmentInjector(): AndroidInjector<Fragment>
            = fragmentDispatchingAndroidInjector

}
