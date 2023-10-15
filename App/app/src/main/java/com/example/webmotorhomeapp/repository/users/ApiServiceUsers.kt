package com.example.webmotorhomeapp.repository.users

import com.example.webmotorhomeapp.data.motorhome.Motorhome
import com.example.webmotorhomeapp.data.user.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServiceUsers {

    @GET("/users/list")
    fun getAll(): Call<List<User>>

    @GET("/users/{id}")
    fun getUserById(@Path("id") id: Int): Call<User>

    @POST("/motorhome")
    fun createUser(@Body user: User): Call<User>
}