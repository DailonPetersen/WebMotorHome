package com.data

import org.jetbrains.exposed.sql.Table

class Endereco (
    val id: Int? = null,
    val idUser: Int,
    val logradouro: String,
    val bairro: String,
    val numero: String,
    val cidade: String,
    val cep: String
)

object Enderecos: Table("enderecos") {
    val id = integer("id").autoIncrement().uniqueIndex()
    val idUser = integer("id_user") references Usuarios.id
    val logradouro = varchar("logradouro", 100)
    val bairro = varchar("logradouro", 30)
    val numero = varchar("logradouro", 5)
    val cidade = varchar("logradouro", 100)
    val cep = varchar("logradouro", 14)
}