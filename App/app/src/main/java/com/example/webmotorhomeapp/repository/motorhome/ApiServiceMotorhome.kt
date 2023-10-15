package com.example.webmotorhomeapp.repository.motorhome

import com.example.webmotorhomeapp.data.motorhome.Motorhome
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServiceMotorhome {

    @GET("/motorhomes")
    fun getAll(): Call<List<Motorhome>>

    @GET("/motorhomes/{id}")
    fun getMotorhomeById(@Path("id") id: Int): Call<Motorhome>

    @POST("/motorhome")
    fun createMotorhome(@Body motorhome: Motorhome): Call<Motorhome>
}