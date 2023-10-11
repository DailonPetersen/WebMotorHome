package com.example.webmotorhomeapp.data.chat

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Message(
    @PrimaryKey val id: Int? = null,
    @ColumnInfo val idAutor: Int,
    @ColumnInfo val descricao: String,
    @ColumnInfo val chatId: String,
)