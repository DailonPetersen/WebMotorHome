package com.routes.controllers

import com.data.Pedido
import com.database.dao.implementations.DAOPedidosInterfaceImpl
import io.ktor.http.*
import io.ktor.server.util.*
import kotlinx.serialization.MissingFieldException
import org.koin.core.component.KoinComponent

class PedidoController: KoinComponent {

    private val dbPedido = DAOPedidosInterfaceImpl()

    suspend fun getAll(): List<Pedido> = dbPedido.getAll()

    suspend fun pedido(id: Int): Pedido = dbPedido.get(id) ?: throw NoSuchElementException()

    suspend fun editPedido(item: Parameters, id: Int): Boolean {
        val pedidoToEdit: Pedido

        return try {
            pedidoToEdit = Pedido(
                idFabrica = item.getOrFail("idFabrica").toInt(),
                idCliente = item.getOrFail("idCliente").toInt(),
                descricao = item.getOrFail("descricao"),
                preco = item.getOrFail("preco").toDouble()
            )


            dbPedido.update(pedidoToEdit)
        } catch (e: MissingFieldException) {
            false
        }

    }

    suspend fun addNewPedido(item: Pedido): Pedido? {
        return dbPedido.insert(item)
    }

    suspend fun remove(id: Int): Boolean = dbPedido.delete(id)
}