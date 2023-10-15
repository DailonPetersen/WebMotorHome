package com.example.webmotorhomeapp.repository.images

import com.example.webmotorhomeapp.data.Image
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServiceImages {

    @POST("image/file/{id}")
    fun sendImages(image: Image, @Path("id") idMotorhome: Int) {

    }
}