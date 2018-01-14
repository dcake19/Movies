package com.example.android.movies.ui

import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import com.example.android.movies.R


abstract class NavigationIconActivity : BaseNavigationActivity() {

    // sets the hamburger icon and the back arrow
    private fun setupToolbarIcon() {
        val actionBarDrawerToggle = ActionBarDrawerToggle(
                this, getDrawerLayout(), getToolbar(), R.string.drawer_open, R.string.drawer_close
        )
        getDrawerLayout().setDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

    protected abstract fun getToolbar(): Toolbar

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        // Sync the toggle state after onRestoreInstanceState has occurred.
        setupToolbarIcon()
    }

}