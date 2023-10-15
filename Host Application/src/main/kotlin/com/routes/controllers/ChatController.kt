package com.routes.controllers

import com.data.Chat
import com.database.dao.facades.DAOChatInterface
import com.database.dao.implementations.DAOChatInterfaceImpl
import com.routes.facades.ChatControllerFacade
import io.ktor.http.*
import io.ktor.server.util.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ChatController: ChatControllerFacade, KoinComponent {

    private val dbChat by inject<DAOChatInterface>()

    override suspend fun getAll(): List<Chat> = dbChat.getAll()

    override suspend fun chat(id: Int): Chat = dbChat.get(id) ?: throw NoSuchElementException()

    override suspend fun editChat(item: Parameters, id: Int): Boolean {
        val chatToEdit = Chat(
            idAnunciante = item.getOrFail("idAnunciante").toInt(),
            idCliente = item.getOrFail("idCliente").toInt(),
            idAnuncio = item.getOrFail("idAnuncio").toInt(),
            nomeAnuncio = item.getOrFail("nomeAnuncio"),
            lastMessage = item.getOrFail("lastMessage"),
        )

        if (chatToEdit.idAnunciante == 0) return false
        if (chatToEdit.idCliente == 0) return false
        if (chatToEdit.idAnuncio == 0) return false
        //if (chatToAdd.precoAluguel.equals(0.00) || chatToAdd.precoCompra.equals(0.00)) return false

        return dbChat.update(chatToEdit)
    }

    override suspend fun addNewChat(item: Chat): Chat? {
        if (item.idAnunciante == 0) return null
        if (item.idCliente == 0) return null
        if (item.idAnuncio == 0) return null
        //if (chatToAdd.precoAluguel.equals(0.00) || chatToAdd.precoCompra.equals(0.00)) return null

        return dbChat.insert(item)
    }

    override suspend fun remove(id: Int): Boolean = dbChat.delete(id)
}