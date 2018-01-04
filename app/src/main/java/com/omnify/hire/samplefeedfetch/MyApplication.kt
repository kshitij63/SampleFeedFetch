package com.omnify.hire.samplefeedfetch

import android.app.Application
import dagger.DaggerMainActivityComponent
import dagger.MainActivityComponent
import dagger.MainActivityModule

/**
 * Created by user on 1/4/2018.
 */
class MyApplication:Application() {
    lateinit var component: MainActivityComponent

    override fun onCreate() {
        super.onCreate()
     component=DaggerMainActivityComponent.builder()
            .mainActivityModule(MainActivityModule(this ))
            .build()
        component.injectMainActivity(this )
    }
}