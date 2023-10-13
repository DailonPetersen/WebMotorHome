package com.data

import org.jetbrains.exposed.sql.Table

class Message(
    val id: Int? = null,
    val idAutor: Int,
    val chatId:  Int,
    val msg: String,
)

object Messages: Table("messages") {
    val id = integer("id").autoIncrement().uniqueIndex()
    val idAutor = integer("id_autor") references Usuarios.id
    val chatId = integer("id_chat") references Chats.id
    val msg = varchar("msg", 255)

    override val primaryKey = PrimaryKey(Messages.id)
}