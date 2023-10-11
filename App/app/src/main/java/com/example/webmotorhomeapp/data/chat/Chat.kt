package com.example.webmotorhomeapp.data.chat

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Chat(
    @PrimaryKey val id: Int,
    @ColumnInfo val idAnunciante: Int,
    @ColumnInfo val idCliente: Int,
    @ColumnInfo val idAnuncio: Int,
    @ColumnInfo val nomeAnucio: String,
    @ColumnInfo val lastMessage: String? = null)