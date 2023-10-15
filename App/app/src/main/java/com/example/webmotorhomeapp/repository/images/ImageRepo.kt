package com.example.webmotorhomeapp.repository.images

import com.example.webmotorhomeapp.data.Image
import com.example.webmotorhomeapp.data.anuncio.Anuncio
import com.example.webmotorhomeapp.network.WebMotorhomeModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageRepo {

    private val _listImages = MutableStateFlow<List<Image>?>(null)
    val images: StateFlow<List<Image>?> = _listImages

    private val service = WebMotorhomeModule.serviceImage()

//    fun getAllImageByMotorhome(id: Int, callback: (List<Anuncio>?, Throwable?) -> Unit) {
//        val call = service.getAll()
//
//        call.enqueue(object : Callback<List<Anuncio>> {
//            override fun onResponse(call: Call<List<Anuncio>>, response: Response<List<Anuncio>>) {
//                if (response.isSuccessful) {
//                    _listAnuncios.value = response.body()
//                } else callback(null, Exception("Erro ao carregadar dados"))
//            }
//
//            override fun onFailure(call: Call<List<Anuncio>>, t: Throwable) {
//                callback(null, t)
//            }
//
//        })
//    }
}