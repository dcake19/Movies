package com.example.android.movies

//import com.example.android.movies.di.DaggerTestAppComponent
import com.example.android.movies.di.DaggerTestAppComponent
import com.example.android.movies.di.TestAppComponent

class TestApp: App() {

    lateinit var component: TestAppComponent

    override fun createComponent(){
        component = DaggerTestAppComponent.create()
        component.inject(this)
    }
}