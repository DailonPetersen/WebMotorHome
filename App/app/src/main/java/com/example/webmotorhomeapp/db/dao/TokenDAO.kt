package com.example.webmotorhomeapp.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.webmotorhomeapp.data.login.AuthToken

@Dao
interface TokenDAO {

    @Query("SELECT * FROM AuthToken ORDER BY AuthToken.id DESC LIMIT 1")
    fun getToken(): AuthToken?
}