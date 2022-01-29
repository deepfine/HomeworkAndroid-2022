package com.example.assignment_kt

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences(context: Context) {

    private val prefsFilename = "prefs"
//    private val prefsKeyEdt = "myEditText"
    private val prefs: SharedPreferences = context.getSharedPreferences(prefsFilename, 0)

    var favoriteJSON: String?
        get() = prefs.getString("favoriteJSON", null)
        set(value) = prefs.edit().putString("favoriteJSON", value).apply()

    var userId: String?
        get() = prefs.getString("userId", "")
        set(value) = prefs.edit().putString("userId", value).apply()

}