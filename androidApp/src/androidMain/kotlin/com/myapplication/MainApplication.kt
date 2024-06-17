package com.myapplication

import android.app.Application
import initialize

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initialize(this)
    }
}
