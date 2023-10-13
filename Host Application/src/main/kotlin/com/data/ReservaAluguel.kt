package com.data

import org.jetbrains.exposed.sql.Table

class ReservaAluguel(
    val id: Int? = null,
    val idMotorHome: Int,
    val idAutor: Int,
    val idAnuncio: Int,
    val dataSolicitadaId: Int,
    val autorizada: Boolean,
    val recusada: Boolean
)

object ReservasDeAluguel: Table("reservas_de_aluguel") {
    val id = integer("id").autoIncrement().uniqueIndex()
    val idMotorHome = integer("id_motorhome") references MotorHomes.id
    val idAutor = integer("id_autor") references Usuarios.id
    val idAnuncio = integer("id_anuncio") references Anuncios.id
    val dataSolicitadaId = integer("id_data_solicitada") references Disponibilidades.id
    val autorizada = bool("autorizada")
    val recusada = bool("recusada")

    override val primaryKey = PrimaryKey(ReservasDeAluguel.id)
}