package com.example.android.movies.ui

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.android.movies.TestApp
import com.example.android.movies.ui.movies.home.MoviesHomeActivity
import com.example.android.movies.ui.movies.home.MoviesHomeFragment
import com.example.android.movies.ui.movies.list.MoviesListActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestMoviesHomeActivity {

    @Rule
    @JvmField
    val actvityTestRule =
            ActivityTestRule<MoviesHomeActivity>(
                    MoviesHomeActivity::class.java,
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
        val activity = actvityTestRule.activity as MoviesHomeActivity
        val fragment1 = activity.fragmentManager
                .findFragmentByTag(MoviesHomeFragment::class.java.name + " 1") as MoviesHomeFragment
        val fragment2 = activity.fragmentManager
                .findFragmentByTag(MoviesHomeFragment::class.java.name + " 2") as MoviesHomeFragment
        val fragment3 = activity.fragmentManager
                .findFragmentByTag(MoviesHomeFragment::class.java.name + " 3") as MoviesHomeFragment
        val fragment4 = activity.fragmentManager
                .findFragmentByTag(MoviesHomeFragment::class.java.name + " 4") as MoviesHomeFragment

        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            fragment1.update(20)
            fragment2.update(20)
            fragment3.update(20)
            fragment4.update(20)
        }

        Thread.sleep(5000)
    }

}