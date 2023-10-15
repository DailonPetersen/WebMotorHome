package com.example.webmotorhomeapp.repository.anuncios

import com.example.webmotorhomeapp.data.anuncio.Anuncio
import com.example.webmotorhomeapp.network.WebMotorhomeModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnunciosRepo() {

    private val _listAnuncios = MutableStateFlow<List<Anuncio>?>(null)
    val anuncios: StateFlow<List<Anuncio>?> = _listAnuncios

    private val _anuncioInserted = MutableStateFlow<Anuncio?>(null)
    val anuncioInserted: StateFlow<Anuncio?> = _anuncioInserted

    private val service = WebMotorhomeModule.serviceAnuncios()

    fun getAllItems(callback: (List<Anuncio>?, Throwable?) -> Unit) {
        val call = service.getAllAnuncios()

        call.enqueue(object : Callback<List<Anuncio>> {
            override fun onResponse(call: Call<List<Anuncio>>, response: Response<List<Anuncio>>) {
                if (response.isSuccessful) {
                    _listAnuncios.value = response.body()
                } else callback(null, Exception("Erro ao carregadar dados"))
            }

            override fun onFailure(call: Call<List<Anuncio>>, t: Throwable) {
                callback(null, t)
            }

        })
    }

    fun insertAnuncio(anuncio: Anuncio, callback: (Anuncio?, Throwable?) -> Unit) {
        val call = service.createAnuncio(anuncio)

        call.enqueue(object : Callback<Anuncio> {
            override fun onResponse(call: Call<Anuncio>, response: Response<Anuncio>) {
                if (response.isSuccessful) {
                    _anuncioInserted.value = response.body()
                } else callback(null, Exception("Erro ao inserir anuncio"))
            }

            override fun onFailure(call: Call<Anuncio>, t: Throwable) {
                callback(null, t)
            }

        })
    }

    companion object {
        fun startRepo() {

        }
    }
}