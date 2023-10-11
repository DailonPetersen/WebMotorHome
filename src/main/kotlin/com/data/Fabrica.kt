package com.data

import org.jetbrains.exposed.sql.Table

data class Fabrica(
    val id: Int? = null,
    val idUser: Int,
    val nomeFantasia: String,
    val razaoSocial: String,
    val email: String,
    val cnpj: String,
)

object Fabricas: Table() {
    val id = integer("id").autoIncrement().uniqueIndex()
    val idUser = integer("id_usuario") references Usuarios.id
    val nomeFantasia = varchar("nome_fantansia", 100)
    val razaoSocial = varchar("razao_social", 100)
    val email = varchar("email", 100)
    val telefone = varchar("telefone", 13)
    val cnpj = varchar("cnpj", 13)
    
    override val primaryKey = PrimaryKey(Fabricas.id)
}