package com.rj.lightwebviewbridge.app

import android.app.Application
import android.os.Debug
import com.rj.lightwebviewbridge.BuildConfig
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}