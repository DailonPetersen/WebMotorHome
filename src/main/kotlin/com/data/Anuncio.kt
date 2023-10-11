package com.data

import org.jetbrains.exposed.sql.Table
import java.time.LocalDate

data class Anuncio(
    val id: Int? = null,
    val idMotorhome: Int,
    val idCriador: Int,
    val precoAluguel: Double? = null,
    val precoVenda: Double? = null,
    val disponivelParaAluguel: Boolean? = null,
    val descricao: String,
    val dataDeDisponibilidadeAluguel: Int? = null,
    val dataDeDisponibilidadeExposicao: Int? = null,
)

object Anuncios: Table("anuncios") {
    val id = integer("id").autoIncrement().uniqueIndex()
    val idMotorhome = integer("id_motorhome") references MotorHomes.id
    val idCriador = integer("id_criador") references Usuarios.id
    val precoAluguel = double("preco_aluguel")
    val precoVenda = double("preco_venda")
    val disponivelParaAluguel = bool("disponivel_para_alugar")
    val descricao = varchar("descricao", 255)
    val dataDeDisponibilidadeAluguel = integer("data_disponibilidade_aluguel") references Disponibilidades.id
    val dataDeDisponibilidadeExposicao = integer("data_disponibilidade_exposicao") references Disponibilidades.id

    override val primaryKey = PrimaryKey(Anuncios.id)
}