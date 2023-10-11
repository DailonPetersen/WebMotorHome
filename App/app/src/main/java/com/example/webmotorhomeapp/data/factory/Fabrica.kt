package com.example.webmotorhomeapp.data.factory

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Fabrica(
    @PrimaryKey
    val id: Int? = null,
    @ColumnInfo val idUser: Int,
    @ColumnInfo val nomeFantasia: String,
    @ColumnInfo val razaoSocial: String,
    @ColumnInfo val email: String,
    @ColumnInfo val cnpj: String,
)