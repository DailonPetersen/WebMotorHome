package com.routes.controllers

import com.data.Pedido
import com.database.dao.facades.DAOPedidosInterface
import com.database.dao.implementations.DAOPedidosInterfaceImpl
import com.routes.facades.PedidoControllerFacade
import io.ktor.http.*
import io.ktor.server.util.*
import kotlinx.serialization.MissingFieldException
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PedidoController: PedidoControllerFacade, KoinComponent {

    private val dbPedido by inject<DAOPedidosInterface>()
    override suspend fun getAll(): List<Pedido> = dbPedido.getAll()

    override suspend fun pedido(id: Int): Pedido = dbPedido.get(id) ?: throw NoSuchElementException()

    override suspend fun editPedido(item: Parameters, id: Int): Boolean {
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

    override suspend fun addNewPedido(item: Pedido): Pedido? {
        return dbPedido.insert(item)
    }

    override suspend fun remove(id: Int): Boolean = dbPedido.delete(id)
}