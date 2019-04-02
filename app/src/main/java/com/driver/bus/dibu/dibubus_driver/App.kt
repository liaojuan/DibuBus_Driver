package com.driver.bus.dibu.dibubus_driver

import android.app.Application

class App : Application() {



    companion object {
        lateinit var instance : App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}