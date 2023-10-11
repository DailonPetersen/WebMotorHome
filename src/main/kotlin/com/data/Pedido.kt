package com.data

import org.jetbrains.exposed.sql.Table

data class Pedido(
    val id: Int? = null,
    val idFabrica: Int,
    val idCliente: Int,
    val descricao: String,
    val preco: Double
)

object Pedidos: Table() {
    val id = integer("id").autoIncrement().uniqueIndex()
    val idFabrica = integer("id_fabrica")
    val idCliente = integer("id_cliente")
    val descricao = varchar("descricao", 100)
    val preco = double("preco")

    override val primaryKey = PrimaryKey(id)
}