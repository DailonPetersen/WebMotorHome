package com.example.webmotorhomeapp.repository.anuncios

import com.example.webmotorhomeapp.data.anuncio.Anuncio
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServiceAnuncios {

    @GET("/anuncios")
    fun getAllAnuncios(): Call<List<Anuncio>>

    @GET("/anuncios/{id}")
    fun getAnuncioById(@Path("id") id: Int): Call<Anuncio>

    @POST("/anuncio")
    fun createAnuncio(@Body anuncio: Anuncio): Call<Anuncio>
}