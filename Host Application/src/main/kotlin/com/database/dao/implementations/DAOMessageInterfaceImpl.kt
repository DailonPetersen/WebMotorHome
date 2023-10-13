package com.database.dao.implementations

import com.data.Message
import com.data.Messages
import com.database.dao.facades.DAOMessageInterface
import com.plugins.DatabaseUtil
import com.plugins.Databases
import mu.KotlinLogging
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DAOMessageInterfaceImpl : DAOMessageInterface {
    private val logger = KotlinLogging.logger("advertisementRepository")

    override suspend fun getAll(): List<Message> = Databases.dbQuery {
        Messages.selectAll().map{ DatabaseUtil.resultRowToMessage(it) }
    }

    override suspend fun get(id: Int): Message? = Databases.dbQuery {
        Messages.select { Messages.id eq id }
            .map{ DatabaseUtil.resultRowToMessage(it) }
            .singleOrNull()
    }

    override suspend fun insert(message: Message): Message? = Databases.dbQuery {
        val statement = Messages.insert {
            it[idAutor] = message.idAutor
            it[chatId] = message.chatId
        }
        statement.resultedValues?.singleOrNull()?.let { DatabaseUtil.resultRowToMessage(it) }
    }

    override suspend fun update(message: Message): Boolean = Databases.dbQuery {
        Messages.update(where = { Messages.id eq message.id as Int }) {
            it[msg] = message.msg
            it[idAutor] = message.idAutor
            it[chatId] = message.chatId
        } > 0
    }

    override suspend fun delete(id: Int): Boolean = Databases.dbQuery {
        Messages.deleteWhere { Messages.id eq id } > 0
    }
}