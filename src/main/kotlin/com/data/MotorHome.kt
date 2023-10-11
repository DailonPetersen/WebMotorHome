package com.data

import org.jetbrains.exposed.sql.Table

data class MotorHome(
    val id: Int? = null,
    val modelo: String,
    val descricao: String,
    val ano: Int,
    val exposicao: Boolean,
    val avaliacao: Int)

object MotorHomes: Table("MotorHomes") {
    val id = integer("id").autoIncrement().uniqueIndex()
    val modelo = varchar("modelo", 100)
    val descricao = varchar("descricao", 1024)
    val ano = integer("ano")
    val exposicao = bool("exposicao")
    val avaliacao = integer("avaliacao")

    override val primaryKey = PrimaryKey(id)
}