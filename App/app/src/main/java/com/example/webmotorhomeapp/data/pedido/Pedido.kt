package com.example.webmotorhomeapp.data.pedido

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pedido(
    @PrimaryKey val id: Int? = null,
    @ColumnInfo val idFabrica: Int,
    @ColumnInfo val idCliente: Int,
    @ColumnInfo val descricao: String,
    @ColumnInfo val preco: Double
)