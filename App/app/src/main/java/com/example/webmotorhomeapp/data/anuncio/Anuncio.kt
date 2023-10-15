package com.example.webmotorhomeapp.data.anuncio

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Anuncio(
    @PrimaryKey val id: Int? = null,
    @ColumnInfo val idMotorhome: Int? = null,
    @ColumnInfo val idCriador: Int,
    @ColumnInfo val precoAluguel: Double? = null,
    @ColumnInfo val precoVenda: Double? = null,
    @ColumnInfo val disponivelParaAluguel: Boolean? = null,
    @ColumnInfo val descricao: String,
    @ColumnInfo var images: List<Int>? = null,
    @ColumnInfo val dataDeDisponibilidadeAluguel: Int? = null,
    @ColumnInfo val dataDeDisponibilidadeExposicao: Int? = null,
)