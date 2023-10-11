package com.example.webmotorhomeapp.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.webmotorhomeapp.data.chat.Chat
import com.example.webmotorhomeapp.data.user.User

@Dao
interface ChatDAO {

    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM chat WHERE id LIKE :id")
    fun findById(id: Int): Chat

    @Query("SELECT * FROM chat WHERE idAnunciante LIKE :idAnunciante")
    fun findByIdAnunciante(idAnunciante: Int): Chat

    @Insert
    fun insertAll(vararg chat: Chat)

    @Delete
    fun delete(chat: Chat)
}