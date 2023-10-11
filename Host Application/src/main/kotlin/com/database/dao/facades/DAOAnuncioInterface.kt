package com.database.dao.facades

import com.data.Anuncio
import com.data.Usuario

interface DAOAnuncioInterface {
    suspend fun getAll(): List<Anuncio>
    suspend fun get(id: Int): Anuncio?
    suspend fun insert(anuncio: Anuncio): Anuncio?
    suspend fun update(anuncio: Anuncio): Boolean
    suspend fun delete(id: Int): Boolean
}