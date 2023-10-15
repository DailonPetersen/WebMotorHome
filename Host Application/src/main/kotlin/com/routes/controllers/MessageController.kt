package com.routes.controllers

import com.data.Message
import com.database.dao.facades.DAOMessageInterface
import com.database.dao.implementations.DAOMessageInterfaceImpl
import com.routes.facades.MessageControllerFacade
import io.ktor.http.*
import io.ktor.server.util.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MessageController: MessageControllerFacade, KoinComponent {

    private val dbMessage by inject<DAOMessageInterface>()

    override suspend fun getAll(): List<Message> = dbMessage.getAll()

    override suspend fun message(id: Int): Message = dbMessage.get(id) ?: throw NoSuchElementException()

    override suspend fun editMessage(item: Parameters, id: Int): Boolean {
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

    override suspend fun addNewMessage(item: Message): Message? {
        if (item.msg.isEmpty() || item.msg.isBlank()) return null
        if (item.chatId == 0) return null
        if (item.idAutor == 0) return null
        //if (messageToAdd.precoAluguel.equals(0.00) || messageToAdd.precoCompra.equals(0.00)) return null

        return dbMessage.insert(item)
    }

    override suspend fun remove(id: Int): Boolean = dbMessage.delete(id)
}