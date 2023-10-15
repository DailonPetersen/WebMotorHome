package com.routes.facades

import com.data.Message
import com.data.Usuario
import io.ktor.http.*
import io.ktor.server.util.*

interface MessageControllerFacade {

    suspend fun getAll(): List<Message>
    suspend fun message(id: Int): Message
    suspend fun editMessage(item: Parameters, id: Int): Boolean
    suspend fun addNewMessage(item: Message): Message?
    suspend fun remove(id: Int): Boolean
}