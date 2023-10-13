package com.database.dao.implementations

import com.data.Chat
import com.data.Chats
import com.database.dao.facades.DAOChatInterface
import com.plugins.DatabaseUtil
import com.plugins.Databases
import mu.KotlinLogging
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DAOChatInterfaceImpl : DAOChatInterface {
    private val logger = KotlinLogging.logger("advertisementRepository")

    override suspend fun getAll(): List<Chat> = Databases.dbQuery {
        Chats.selectAll().map{ DatabaseUtil.resultRowToChat(it) }
    }

    override suspend fun get(id: Int): Chat? = Databases.dbQuery {
        Chats.select { Chats.id eq id }
            .map{ DatabaseUtil.resultRowToChat(it) }
            .singleOrNull()
    }

    override suspend fun insert(chat: Chat): Chat? = Databases.dbQuery {
        val statement = Chats.insert {
            it[idAnunciante] = chat.idAnunciante
            it[idCliente] = chat.idCliente
            it[idAnuncio] = chat.idAnuncio
            it[nomeAnuncio] = chat.nomeAnuncio
            it[lastMessage] = chat.lastMessage ?: "mensagem não encontrada!"
        }
        statement.resultedValues?.singleOrNull()?.let { DatabaseUtil.resultRowToChat(it) }
    }

    override suspend fun update(chat: Chat): Boolean = Databases.dbQuery {
        Chats.update(where = { Chats.id eq chat.id as Int }) {
            it[idAnunciante] = chat.idAnunciante
            it[idCliente] = chat.idCliente
            it[idAnuncio] = chat.idAnuncio
            it[nomeAnuncio] = chat.nomeAnuncio
            it[lastMessage] = chat.lastMessage ?: "mensagem não encontrada!"
        } > 0
    }

    override suspend fun delete(id: Int): Boolean = Databases.dbQuery {
        Chats.deleteWhere { Chats.id eq id } > 0
    }
}