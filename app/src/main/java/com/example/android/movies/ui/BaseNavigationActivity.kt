package com.example.android.movies.ui

import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.navigation_drawer.*


abstract class BaseNavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResourceId())
        navigation_drawer.setPadding(0, getStatusBarHeight(), 0, 0)
    }

    //the layout of the sub class
    protected abstract fun getLayoutResourceId(): Int

    // the id of the view relating to the activity currently being displayed
    protected abstract fun getViewId(): Int

    // the drawer layout in the activity
    protected abstract fun getDrawerLayout(): DrawerLayout

    fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
}