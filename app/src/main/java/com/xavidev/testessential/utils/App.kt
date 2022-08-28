package com.xavidev.testessential.utils

import android.app.Application
import android.content.Context

class App : Application() {


    companion object {
        private lateinit var instance: App

        fun getInstance(): App = instance

        fun getContext(): Context = instance
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}