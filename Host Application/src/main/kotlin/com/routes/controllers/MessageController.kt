package com.routes.controllers

import com.data.Message
import com.database.dao.implementations.DAOMessageInterfaceImpl
import io.ktor.http.*
import io.ktor.server.util.*
import org.koin.core.component.KoinComponent


class MessageController: KoinComponent {

    private val dbMessage = DAOMessageInterfaceImpl()

    suspend fun getAll(): List<Message> = dbMessage.getAll()

    suspend fun message(id: Int): Message = dbMessage.get(id) ?: throw NoSuchElementException()

    suspend fun editMessage(item: Parameters, id: Int): Boolean {
        val messageToEdit = Message(
            idAutor = item.getOrFail("idAutor").toInt(),
            chatId = item.getOrFail("chatId").toInt(),
            msg = item.getOrFail("msg"),
        )

        if (messageToEdit.msg.isEmpty() || messageToEdit.msg.isBlank()) return false
        if (messageToEdit.chatId == 0) return false
        if (messageToEdit.idAutor == 0) return false
        //if (messageToAdd.precoAluguel.equals(0.00) || messageToAdd.precoCompra.equals(0.00)) return false

        return dbMessage.update(messageToEdit)
    }

    suspend fun addNewMessage(item: Message): Message? {
        if (item.msg.isEmpty() || item.msg.isBlank()) return null
        if (item.chatId == 0) return null
        if (item.idAutor == 0) return null
        //if (messageToAdd.precoAluguel.equals(0.00) || messageToAdd.precoCompra.equals(0.00)) return null

        return dbMessage.insert(item)
    }

    suspend fun remove(id: Int): Boolean = dbMessage.delete(id)
}