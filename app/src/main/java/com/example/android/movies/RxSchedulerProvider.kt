package com.example.android.movies

import io.reactivex.Scheduler

interface RxSchedulerProvider {
    fun subscribeOn():Scheduler
    fun observeOn():Scheduler
}