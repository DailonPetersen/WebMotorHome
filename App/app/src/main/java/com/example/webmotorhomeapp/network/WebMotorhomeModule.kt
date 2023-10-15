package com.example.webmotorhomeapp.network

import com.example.webmotorhomeapp.repository.anuncios.ApiServiceAnuncios
import com.example.webmotorhomeapp.repository.images.ApiServiceImages
import com.example.webmotorhomeapp.repository.motorhome.ApiServiceMotorhome
import com.example.webmotorhomeapp.repository.users.ApiServiceUsers
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object WebMotorhomeModule {
    val BASE_URL = "http://127.0.0.1:5050/"

    fun client() = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        })
        .build()

    val gsonBuilder = GsonBuilder().create()

    fun retrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client())
        .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
        .build()


    fun serviceMotorhome(): ApiServiceMotorhome = retrofit().create(ApiServiceMotorhome::class.java)
    fun serviceAnuncios(): ApiServiceAnuncios = retrofit().create(ApiServiceAnuncios::class.java)
    fun serviceUsers(): ApiServiceUsers = retrofit().create(ApiServiceUsers::class.java)
    fun serviceImage(): ApiServiceImages = retrofit().create(ApiServiceImages::class.java)
}
