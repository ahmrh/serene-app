package com.ahmrh.serene

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SereneApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}

