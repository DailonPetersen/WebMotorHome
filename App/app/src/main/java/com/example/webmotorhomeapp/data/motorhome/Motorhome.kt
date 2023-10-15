package com.example.webmotorhomeapp.data.motorhome

import android.graphics.drawable.Drawable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.webmotorhomeapp.data.Image

@Entity
data class Motorhome(
    @PrimaryKey val id: Int? = null,
    @ColumnInfo val modelo: String,
    @ColumnInfo val descricao: String,
    @ColumnInfo val fabricante: String,
    @ColumnInfo val placa: String,
    @ColumnInfo val ano: Int,
    @ColumnInfo val exposicao: Boolean,
    @ColumnInfo val avaliacao: Int,
    var listImages: List<Drawable>?
    )
