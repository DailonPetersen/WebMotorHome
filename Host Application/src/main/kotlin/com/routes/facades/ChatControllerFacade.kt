package com.routes.facades

import com.data.Chat
import io.ktor.http.*

interface ChatControllerFacade {

    suspend fun getAll(): List<Chat>

    suspend fun chat(id: Int): Chat

    suspend fun editChat(item: Parameters, id: Int): Boolean

    suspend fun addNewChat(item: Chat): Chat?

    suspend fun remove(id: Int): Boolean
}