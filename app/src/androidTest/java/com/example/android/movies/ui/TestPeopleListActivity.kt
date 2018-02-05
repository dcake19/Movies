package com.example.android.movies.ui

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.android.movies.TestApp
import com.example.android.movies.ui.people.list.PeopleListActivity
import com.example.android.movies.ui.people.list.PeopleListFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestPeopleListActivity {

    @Rule
    @JvmField
    val actvityTestRule =
            ActivityTestRule<PeopleListActivity>(
                    PeopleListActivity::class.java,
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
        val activity = actvityTestRule.activity as PeopleListActivity
        val fragment = activity.fragmentManager
                .findFragmentByTag(PeopleListFragment::class.java.name) as PeopleListFragment

        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            fragment.display(20)
        }
        Thread.sleep(5000)

    }

}