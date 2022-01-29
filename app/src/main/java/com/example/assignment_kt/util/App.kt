package com.example.assignment_kt.util

import android.app.Application
import com.example.assignment_kt.MySharedPreferences

class App : Application() {

    companion object {
        lateinit var prefs : MySharedPreferences
    }

    override fun onCreate() {
        prefs = MySharedPreferences(applicationContext)
        super.onCreate()
    }
}