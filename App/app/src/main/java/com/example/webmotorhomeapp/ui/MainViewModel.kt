package com.example.webmotorhomeapp.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.webmotorhomeapp.db.AppDatabase

class MainViewModel(app: Application): AndroidViewModel(app) {

    private val _dbInstance = MutableLiveData<AppDatabase>().apply {
        value = AppDatabase.getInstanceDatabase(app)
    }

    private fun startDB(app: Context) {
        _dbInstance.value = AppDatabase.getInstanceDatabase(app)
    }

    val dbInstance: LiveData<AppDatabase> = _dbInstance
}