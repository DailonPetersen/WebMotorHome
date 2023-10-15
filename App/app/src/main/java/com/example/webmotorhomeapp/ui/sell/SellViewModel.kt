package com.example.webmotorhomeapp.ui.sell

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.webmotorhomeapp.data.Image
import com.example.webmotorhomeapp.data.anuncio.Anuncio
import com.example.webmotorhomeapp.data.motorhome.Motorhome
import com.example.webmotorhomeapp.repository.anuncios.AnunciosRepo
import com.example.webmotorhomeapp.repository.images.ImageRepo
import com.example.webmotorhomeapp.repository.motorhome.MotorhomeRepo
import kotlinx.coroutines.flow.StateFlow

class SellViewModel: ViewModel() {

    private val anunciosRepo = AnunciosRepo()
    private val motorhomeRepo = MotorhomeRepo()
    private val imageRepo = ImageRepo()

    var anuncios: StateFlow<List<Anuncio>?> = anunciosRepo.anuncios
    var motorhomes: StateFlow<List<Motorhome>?> = motorhomeRepo.motorhomes

    var anuncio: StateFlow<Anuncio?> = anunciosRepo.anuncioInserted
    var motorhome: StateFlow<Motorhome?> = motorhomeRepo.motorhome


    fun createImage(image: Image) {
        imageRepo.images
    }

    fun createAnuncio(anuncio: Anuncio): Anuncio? {
        var anuncioToReturn: Anuncio? = null
        anunciosRepo.insertAnuncio(anuncio) { anuncioCriado, error ->
            if (anuncioCriado != null) {
                Log.e("DAILON", "lista $anuncioCriado")
                anuncioToReturn = anuncioCriado
            } else {
                Log.e("DAILON", "error $error")
            }
        }
        return anuncioToReturn
    }

    fun createMotorhome(motorhome: Motorhome): Motorhome? {
        var motorhomeToReturn: Motorhome? = null
        motorhomeRepo.insertMotorhome(motorhome) { motorhomeCriado, error ->
            if (motorhomeCriado != null) {
                Log.e("DAILON", "lista $motorhomeCriado")
                motorhomeToReturn = motorhomeCriado
            } else {
                Log.e("DAILON", "error $error")
            }
        }
        return motorhomeToReturn
    }

}