package com.example.android.movies.ui.movies.list

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import com.example.android.movies.R
import com.example.android.movies.ui.BaseNavigationActivity
import com.example.android.movies.ui.NavigationIconActivity
import com.example.android.movies.ui.movies.MoviesDownloadTypes
import com.example.android.movies.ui.movies.home.MoviesHomeActivity
import com.example.android.movies.ui.movies.home.MoviesHomeFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import kotlinx.android.synthetic.main.movies_list_activity.*
import kotlinx.android.synthetic.main.movies_list_appbar.*
import javax.inject.Inject

class MoviesListActivity :BaseNavigationActivity(), HasFragmentInjector {

    companion object {
        const val DOWNLOAD_TYPE_KEY = "download_key"

        fun getIntent(context: Context,type:Int): Intent {
            val intent = Intent(context,MoviesListActivity::class.java)
            intent.putExtra(DOWNLOAD_TYPE_KEY,type)
            return intent
        }
    }

    @Inject
    lateinit var fragmentDispatchingAndroidInjector:
            DispatchingAndroidInjector<Fragment>

    private var type :Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        type = intent.getIntExtra(DOWNLOAD_TYPE_KEY,MoviesDownloadTypes.NOW_PLAYING)

        if (savedInstanceState == null)
            setFragment(type)

        setToolbar(type)
    }

    private fun setFragment(type:Int){
        val fragment = MoviesListFragment()
        val bundle = Bundle()
        bundle.putInt(MoviesHomeActivity.DOWNLOAD_TYPE_KEY,type)
        fragment.arguments = bundle
        val ft = fragmentManager.beginTransaction()
//        ft.setCustomAnimations(
//                R.anim.abc_fade_in, R.anim.abc_fade_out)
        ft.replace(R.id.movies_list_content,fragment)
        ft.commit()
    }

    private fun setToolbar(type:Int){
        when(type){
            MoviesDownloadTypes.NOW_PLAYING ->
                text_toolbar_title.text = getString(R.string.now_playing)
            MoviesDownloadTypes.UPCOMING ->
                text_toolbar_title.text = getString(R.string.upcoming)
            MoviesDownloadTypes.TOP_RATED ->
                text_toolbar_title.text = getString(R.string.top_rated)
            MoviesDownloadTypes.POPULAR ->
                text_toolbar_title.text = getString(R.string.popular)
        }
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.movies_list_activity
    }

    override fun getViewId(): Int {
        when(type){
            MoviesDownloadTypes.NOW_PLAYING ->
                return R.id. navigation_now_playing
            MoviesDownloadTypes.UPCOMING ->
                return R.id. navigation_upcoming
            MoviesDownloadTypes.TOP_RATED ->
                return R.id. navigation_top_rated
            MoviesDownloadTypes.POPULAR ->
                return R.id.navigation_popular
        }
        return -1
    }

    override fun getDrawerLayout(): DrawerLayout {
        return movies_list_drawer_layout
    }

    override fun fragmentInjector(): AndroidInjector<Fragment>
            = fragmentDispatchingAndroidInjector
}
