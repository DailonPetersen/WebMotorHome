package com.data

import org.jetbrains.exposed.sql.Table

data class Usuario (
    val id: Int? = null,
    val nome: String,
    val sobrenome: String,
    val email: String,
    val password: String,
    val telefone: String
) {
    lateinit var listaFavoritos: ArrayList<Int>
    lateinit var listaAnuncios: ArrayList<Int>
}

object Usuarios: Table() {
    val id = integer("id").autoIncrement().uniqueIndex()
    val nome = varchar("nome", 30)
    val sobrenome = varchar("sobrenome", 100)
    val email = varchar("email", 100)
    val password = varchar("password", 20)
    val telefone = varchar("telefone", 13)

    override val primaryKey = PrimaryKey(id)
}

