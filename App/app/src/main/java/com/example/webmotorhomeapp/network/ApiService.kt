package com.example.webmotorhomeapp.network

import com.example.webmotorhomeapp.data.user.User
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/users/list")
    fun getAllUsers(): Call<List<User>>
}