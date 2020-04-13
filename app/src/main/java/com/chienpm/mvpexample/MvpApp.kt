package com.chienpm.mvpexample

import android.app.Application

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