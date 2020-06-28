package com.enterprise.test

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class App: Application() {

    companion object{var instance: App? = null}

    var sharedPreferences: SharedPreferences? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        sharedPreferences =
            getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        sharedPreferences!!.edit().putString("token", null).apply()
    }
}