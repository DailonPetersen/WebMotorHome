package com.example.webmotorhomeapp.repository.motorhome

import com.example.webmotorhomeapp.data.anuncio.Anuncio
import com.example.webmotorhomeapp.data.motorhome.Motorhome
import com.example.webmotorhomeapp.network.WebMotorhomeModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MotorhomeRepo {

    private val _listMotorhome = MutableStateFlow<List<Motorhome>?>(null)
    val motorhomes: StateFlow<List<Motorhome>?> = _listMotorhome

    private val _motorhome = MutableStateFlow<Motorhome?>(null)
    val motorhome: StateFlow<Motorhome?> = _motorhome

    private val service = WebMotorhomeModule.serviceMotorhome()

    fun getAllItems(callback: (List<Motorhome>?, Throwable?) -> Unit) {
        val call = service.getAll()

        call.enqueue(object : Callback<List<Motorhome>> {
            override fun onResponse(call: Call<List<Motorhome>>, response: Response<List<Motorhome>>) {
                if (response.isSuccessful) {
                    _listMotorhome.value = response.body()
                } else callback(null, Exception("Erro ao carregadar dados"))
            }

            override fun onFailure(call: Call<List<Motorhome>>, t: Throwable) {
                callback(null, t)
            }
        })
    }

    fun insertMotorhome(motorhome: Motorhome, callback: (Motorhome?, Throwable?) -> Unit) {
        val call = service.createMotorhome(motorhome)

        call.enqueue(object : Callback<Motorhome> {
            override fun onResponse(call: Call<Motorhome>, response: Response<Motorhome>) {
                if (response.isSuccessful) {
                    _motorhome.value = response.body()
                } else callback(null, Exception("Erro ao inserir anuncio"))
            }

            override fun onFailure(call: Call<Motorhome>, t: Throwable) {
                callback(null, t)
            }

        })
    }
}