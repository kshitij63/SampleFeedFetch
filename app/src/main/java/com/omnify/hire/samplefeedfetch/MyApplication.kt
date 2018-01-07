package com.omnify.hire.samplefeedfetch

import android.app.Application
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import dagger.DaggerMainActivityComponent
import dagger.MainActivityComponent
import dagger.MainActivityModule

/**
 * Created by user on 1/4/2018.
 */
class MyApplication : Application() {
    companion object {
        lateinit var component: MainActivityComponent

    }

    override fun onCreate() {
        super.onCreate()
        AppEventsLogger.activateApp(this)
        component = DaggerMainActivityComponent.builder()
                .mainActivityModule(MainActivityModule(this))
                .build()

    }
}