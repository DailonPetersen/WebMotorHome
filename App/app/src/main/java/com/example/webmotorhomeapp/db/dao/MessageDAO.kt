package com.example.webmotorhomeapp.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.webmotorhomeapp.data.chat.Chat
import com.example.webmotorhomeapp.data.chat.Message
import com.example.webmotorhomeapp.data.user.User

@Dao
interface MessageDAO {

    @Query("SELECT * FROM message")
    fun getAll(): List<Message>

    @Query("SELECT * FROM message WHERE id LIKE :id")
    fun findById(id: Int): Message

    @Query("SELECT * FROM message WHERE idAutor LIKE :idAutor")
    fun findByIdAutor(idAutor: Int): List<Chat>

    @Insert
    fun insertAll(vararg message: Message)

    @Delete
    fun delete(message: Message)
}