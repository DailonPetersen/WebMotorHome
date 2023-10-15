package com.routes.facades

import com.data.Pedido
import io.ktor.http.*
import io.ktor.server.util.*
import kotlinx.serialization.MissingFieldException

interface PedidoControllerFacade {

    suspend fun getAll(): List<Pedido>

    suspend fun pedido(id: Int): Pedido

    suspend fun editPedido(item: Parameters, id: Int): Boolean

    suspend fun addNewPedido(item: Pedido): Pedido?

    suspend fun remove(id: Int): Boolean
}