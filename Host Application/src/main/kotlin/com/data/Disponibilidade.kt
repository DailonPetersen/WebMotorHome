package com.data

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date
import java.time.LocalDate

data class Disponibilidade(
    val id: Int? = null,
    val dataInicio: LocalDate,
    val dataFim: LocalDate
)

object Disponibilidades: Table("disponibilidades") {
    val id = integer("id").autoIncrement().uniqueIndex()
    val dataInicio = date("data_inicio")
    val dataFim = date("data_fim")

    override val primaryKey = PrimaryKey(id)
}