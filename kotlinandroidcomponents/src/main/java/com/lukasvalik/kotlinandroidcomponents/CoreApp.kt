package com.lukasvalik.kotlinandroidcomponents

import android.app.Application
import com.lukasvalik.kotlinandroidcomponents.util.ReleaseTree
import timber.log.Timber

abstract class CoreApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.DebugTree()
        } else {
            // crashlytics.start() // Init crash logging lib
            ReleaseTree()
        }
    }
}