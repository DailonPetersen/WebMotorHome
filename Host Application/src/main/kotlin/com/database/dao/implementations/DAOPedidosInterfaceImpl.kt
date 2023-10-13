package com.database.dao.implementations

import com.data.Pedido
import com.data.Pedidos
import com.database.dao.facades.DAOPedidosInterface
import com.plugins.DatabaseUtil
import com.plugins.Databases.dbQuery
import mu.KotlinLogging
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DAOPedidosInterfaceImpl: DAOPedidosInterface {

    private val logger = KotlinLogging.logger("OrdersRepository")

    override suspend fun getAll(): List<Pedido> = dbQuery {
        Pedidos.selectAll().map { DatabaseUtil.resultRowToOrder(it) }
    }

    override suspend fun get(id: Int): Pedido? = dbQuery {
        Pedidos.select { Pedidos.id eq id }
            .map { DatabaseUtil.resultRowToOrder(it) }
            .singleOrNull()
    }

    override suspend fun insert(pedido: Pedido): Pedido? = dbQuery {
        val statementInsert = Pedidos.insert {
            it[idFabrica] = pedido.idFabrica
            it[idCliente] = pedido.idCliente
            it[descricao] = pedido.descricao
            it[preco] = pedido.preco
        }
        statementInsert.resultedValues?.singleOrNull()?.let{ DatabaseUtil.resultRowToOrder(it) }
    }

    override suspend fun update(pedido: Pedido): Boolean = dbQuery {
        Pedidos.update( {Pedidos.id eq pedido.id as Int } ) {
            it[idFabrica] = pedido.idFabrica
            it[idCliente] = pedido.idCliente
            it[descricao] = pedido.descricao
            it[preco] = pedido.preco
        } > 0
    }

    override suspend fun delete(id: Int): Boolean = dbQuery {
        Pedidos.deleteWhere { Pedidos.id eq id } > 0
    }
}