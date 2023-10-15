package com.routes.facades

import com.data.Fabrica
import io.ktor.http.*
import io.ktor.server.util.*

interface FabricaControllerFacade {

    suspend fun getAll(): List<Fabrica>

    suspend fun fabrica(id: Int): Fabrica

    suspend fun editFabrica(item: Parameters, id: Int): Boolean

    suspend fun addNewFabrica(item: Fabrica): Fabrica?

    suspend fun remove(id: Int): Boolean
}