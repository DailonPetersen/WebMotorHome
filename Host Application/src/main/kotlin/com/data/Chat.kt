package com.data

import com.data.Anuncios.autoIncrement
import com.data.Anuncios.references
import com.data.Anuncios.uniqueIndex
import com.routes.UserRoutes
import org.jetbrains.exposed.sql.Table

class Chat(
    val id: Int? = null,
    val idAnunciante: Int,
    val idCliente: Int,
    val idAnuncio:  Int,
    val nomeAnuncio: String,
    val lastMessage: String? = null
)

object Chats: Table("chats") {
    val id = integer("id").autoIncrement().uniqueIndex()
    val idAnunciante = integer("id_anunciante") references Usuarios.id
    val idCliente = integer("id_cliente") references Usuarios.id
    val idAnuncio = integer("id_anuncio") references Anuncios.id
    val nomeAnuncio = varchar("nome_anuncio", 100)
    val lastMessage = varchar("last_message", 255).nullable()

    override val primaryKey = PrimaryKey(Chats.id)
}