package com.example.webmotorhomeapp.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.webmotorhomeapp.data.anuncio.Anuncio

interface AnuncioDAO {

    @Query("SELECT * FROM Anuncio")
    fun getAll(): List<Anuncio>

    @Query("SELECT * FROM Anuncio WHERE id IN (:anuncioIds)")
    fun loadAllByIds(anuncioIds: IntArray): List<Anuncio>

    @Query("SELECT * FROM Anuncio WHERE id LIKE :idAnuncio LIMIT 1")
    fun findAnuncioById(idAnuncio: Int): Anuncio

    @Insert
    fun insertAll(anuncios: List<Anuncio>)

    @Insert
    fun insertAnuncio(anuncio: Anuncio)

    @Delete
    fun delete(Anuncio: Anuncio)
}