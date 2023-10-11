package com.database.dao.facades

import com.data.Chat

interface DAOChatInterface {
    suspend fun getAll(): List<Chat>
    suspend fun get(id: Int): Chat?
    suspend fun insert(chat: Chat): Chat?
    suspend fun update(chat: Chat): Boolean
    suspend fun delete(id: Int): Boolean
}