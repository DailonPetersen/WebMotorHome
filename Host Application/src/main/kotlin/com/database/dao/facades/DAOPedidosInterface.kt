package com.database.dao.facades

import com.data.Fabrica
import com.data.Pedido

interface DAOPedidosInterface {
    suspend fun getAll(): List<Pedido>
    suspend fun get(id: Int): Pedido?
    suspend fun insert(pedido: Pedido): Pedido?
    suspend fun update(pedido: Pedido): Boolean
    suspend fun delete(id: Int): Boolean
}