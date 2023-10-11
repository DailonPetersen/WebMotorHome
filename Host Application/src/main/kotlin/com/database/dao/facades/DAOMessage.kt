package com.database.dao.facades

import com.data.Message

interface DAOMessageInterface {
    suspend fun getAll(): List<Message>
    suspend fun get(id: Int): Message?
    suspend fun insert(message: Message): Message?
    suspend fun update(message: Message): Boolean
    suspend fun delete(id: Int): Boolean
}