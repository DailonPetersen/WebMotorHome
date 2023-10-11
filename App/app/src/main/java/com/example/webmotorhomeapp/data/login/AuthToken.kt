package com.example.webmotorhomeapp.data.login

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class AuthToken(
    @PrimaryKey val id: Int? = null,
    @ColumnInfo val accessToken: String,
    @ColumnInfo val accessTokenExpirationTime: Long,
    @ColumnInfo val refreshToken: String,
    @ColumnInfo val refreshTokenExpirationTime: Long,
    @ColumnInfo val authenticationDateMili: Long
) {
    @Ignore
    constructor() : this(null, "", 0, "", 0,0)

    fun isTokenValid(): Boolean {
        val dateNow = Date().time
        val expirationDate = authenticationDateMili + (accessTokenExpirationTime * 100)

        return dateNow <= expirationDate
    }
}