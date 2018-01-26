package com.example.android.movies

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.example.android.movies.di.movies.detailed.info.MoviesInfoModule
import com.example.android.movies.ui.movies.detailed.info.MoviesInfoInteractor
import com.example.android.movies.ui.movies.detailed.info.MoviesInfoPresenter
import org.mockito.Matchers.any
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class MovieDetailsTest {

    lateinit var moviesInfoPresenter: MoviesInfoPresenter
    var context:Context? = null

    @Before
    fun init(){

        context = InstrumentationRegistry.getInstrumentation().context

        val moviesInfoModule = mock(MoviesInfoModule::class.java)

        moviesInfoPresenter = mock(MoviesInfoPresenter::class.java)
        `when`(moviesInfoModule.provideMoviesPresenter(
                any(MoviesInfoInteractor::class.java),
                any(RxSchedulerProvider::class.java))).thenReturn(moviesInfoPresenter)


        val instrumentation = InstrumentationRegistry.getInstrumentation()

    }


}