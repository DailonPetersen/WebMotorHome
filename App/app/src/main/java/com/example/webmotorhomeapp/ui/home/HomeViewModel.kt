package com.example.webmotorhomeapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.webmotorhomeapp.data.anuncio.Anuncio
import com.example.webmotorhomeapp.data.motorhome.Motorhome
import com.example.webmotorhomeapp.repository.anuncios.AnunciosRepo
import com.example.webmotorhomeapp.repository.motorhome.MotorhomeRepo
import kotlinx.coroutines.flow.StateFlow


class HomeViewModel() : ViewModel() {

    private val anunciosRepo = AnunciosRepo()
    private val motorhomeRepo = MotorhomeRepo()
    var anuncios: StateFlow<List<Anuncio>?> = anunciosRepo.anuncios
    var motorhomes: StateFlow<List<Motorhome>?> = motorhomeRepo.motorhomes

    fun fetchItems() {
        anunciosRepo.getAllItems { _list, error ->
            if (_list != null) {
                Log.e("DAILON", "lista $_list")
            } else {
                Log.e("DAILON", "error $error")
            }
        }
        motorhomeRepo.getAllItems { _list, error ->
            if (_list != null) {
                Log.e("DAILON", "lista $_list")
            } else {
                Log.e("DAILON", "error $error")
            }
        }
    }
}