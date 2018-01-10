package com.example.android.movies.ui.movies

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.android.movies.BuildConfig
import com.example.android.movies.R

class MoviesHomeActivity : AppCompatActivity() {

    companion object {
        const val DOWNLOAD_TYPE_KEY = "download_key"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movies_home_activity)

        if (savedInstanceState == null) {
            setFragment(MoviesDownloadTypes.NOW_PLAYING)
            setFragment(MoviesDownloadTypes.UPCOMING)
            setFragment(MoviesDownloadTypes.TOP_RATED)
        }
    }

    private fun setFragment(type:Int){
        val nowPlayingFragment = MoviesHomeFragment()
        var bundle = Bundle()
        bundle.putInt(DOWNLOAD_TYPE_KEY,type)
        nowPlayingFragment.arguments = bundle
        val ft = supportFragmentManager.beginTransaction()
        ft.setCustomAnimations(
                R.anim.abc_fade_in, R.anim.abc_fade_out)
        ft.replace(getContent(type),nowPlayingFragment);
        ft.commit();
    }

    private fun getContent(type:Int): Int{
        when(type){
            MoviesDownloadTypes.NOW_PLAYING -> return R.id.now_playing_content
            MoviesDownloadTypes.UPCOMING -> return R.id.upcoming_content
            MoviesDownloadTypes.TOP_RATED -> return R.id.top_rated_content
            else->{return -1}
        }
    }
}
