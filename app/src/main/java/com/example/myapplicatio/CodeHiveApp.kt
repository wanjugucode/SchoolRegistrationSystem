package com.example.myapplicatio

import android.app.Application
import android.content.Context


@HiltAndroidApp
class CodeHiveApp: Application() {
    companion object{
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}