package com.example.webmotorhomeapp

import android.app.Application
import android.content.Context
import com.example.webmotorhomeapp.db.AppDatabase
import com.example.webmotorhomeapp.network.WebMotorhomeModule

private const val KEY_APP_PREFERENCES = "web-motor-home"
private const val KEY_APP_TOKEN = "web-motor-home-app-token"

class WebMotorhomeApplication: Application() {

    companion object {
        private lateinit var instance: WebMotorhomeApplication

        private val preferences by lazy {
            instance.getSharedPreferences(KEY_APP_PREFERENCES, Context.MODE_PRIVATE)
        }

        fun saveToken(token: String) {
            preferences.edit().putString(KEY_APP_TOKEN, token).apply()
        }
        fun getToken(token: String) {
            preferences.getString(KEY_APP_TOKEN, null)
        }


    }

    val dbInstance: AppDatabase by lazy { AppDatabase.getInstanceDatabase(this) }

    override fun onCreate() {
        super.onCreate()

    }
}