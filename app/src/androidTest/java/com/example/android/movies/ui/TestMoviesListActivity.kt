package com.example.android.movies.ui

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.android.movies.TestApp
import com.example.android.movies.ui.movies.list.MoviesListActivity
import com.example.android.movies.ui.movies.BaseMoviesListFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestMoviesListActivity {

    @Rule
    @JvmField
    val actvityTestRule =
            ActivityTestRule<MoviesListActivity>(
                    MoviesListActivity::class.java,
                    false,false)

    @Before
    fun init(){
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val app = instrumentation.targetContext.applicationContext as TestApp
        val component = app.component
        component.inject(this)
    }


    @Test
    fun test() {
        actvityTestRule.launchActivity(Intent())
        val activity = actvityTestRule.activity as MoviesListActivity
        val fragment = activity.fragmentManager
                .findFragmentByTag(BaseMoviesListFragment::class.java.name) as BaseMoviesListFragment

        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            fragment.update(20)
        }
        Thread.sleep(5000)

    }
}