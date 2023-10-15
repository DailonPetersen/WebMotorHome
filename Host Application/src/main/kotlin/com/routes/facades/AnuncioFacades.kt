package com.routes.facades

import com.data.Anuncio
import io.ktor.http.*
import io.ktor.server.util.*

interface AnuncioFacades {

    suspend fun getAll(): List<Anuncio>

    suspend fun anuncio(id: Int): Anuncio

    suspend fun editAnuncio(item: Parameters, id: Int): Boolean

    suspend fun addNewAnuncio(item: Anuncio): Anuncio?

    suspend fun remove(id: Int): Boolean
}