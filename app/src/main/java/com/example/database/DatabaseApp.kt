package com.example.database

import android.app.Application
import android.content.Context

class DatabaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {
        lateinit var appContext: Context
            private set
    }
}