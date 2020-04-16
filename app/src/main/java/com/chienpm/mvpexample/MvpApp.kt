package com.chienpm.mvpexample

import android.app.Application
import com.chienpm.mvpexample.data.database.AppDatabase

class MvpApp: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: MvpApp
            private set
    }
}