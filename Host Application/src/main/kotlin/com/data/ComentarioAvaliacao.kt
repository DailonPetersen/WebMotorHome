package com.data

import org.jetbrains.exposed.sql.Table

class ComentarioAvaliacao(
    val id: Int? = null,
    val idMotorHome: Int,
    val idAutor: Int,
    val idAnuncio: Int? = null,
    val nota: Int,
    val descricao: String
)

object ComentariosAvaliacoes: Table("comentarios_avaliacoes") {
    val id = integer("id").autoIncrement().uniqueIndex()
    val idMotorHome = integer("id_motorhome") references MotorHomes.id
    val idAutor = integer("id_autor") references Usuarios.id
    val idAnuncio = integer("id_anuncio").nullable()
    val nota = integer("nota")
    val descricao = varchar("descricao", 255)

    override val primaryKey = PrimaryKey(ComentariosAvaliacoes.id)
}