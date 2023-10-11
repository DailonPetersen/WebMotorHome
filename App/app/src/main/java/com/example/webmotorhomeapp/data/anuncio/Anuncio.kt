package com.example.webmotorhomeapp.data.anuncio

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Anuncio(
    @PrimaryKey val id: Int? = null,
    @ColumnInfo val idMotorhome: Int,
    @ColumnInfo val idCriador: Int,
    @ColumnInfo val precoAluguel: Double? = null,
    @ColumnInfo val precoVenda: Double? = null,
    @ColumnInfo val disponivelParaAluguel: Boolean? = null,
    @ColumnInfo val descricao: String,
    @ColumnInfo val dataDeDisponibilidadeAluguel: Int? = null,
    @ColumnInfo val dataDeDisponibilidadeExposicao: Int? = null,
)