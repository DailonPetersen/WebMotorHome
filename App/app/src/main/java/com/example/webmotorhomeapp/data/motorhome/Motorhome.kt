package com.example.webmotorhomeapp.data.motorhome

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Motorhome(
    @PrimaryKey val id: Int? = null,
    @ColumnInfo val modelo: String,
    @ColumnInfo val descricao: String,
    @ColumnInfo val ano: Int,
    @ColumnInfo val exposicao: Boolean,
    @ColumnInfo val avaliacao: Int)
