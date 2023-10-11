package com.example.webmotorhomeapp.data.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val id: Int? = null,
    @ColumnInfo val nome: String,
    @ColumnInfo val sobrenome: String,
    @ColumnInfo val email: String,
    @ColumnInfo val telefone: String
) {
    lateinit var listaFavoritos: ArrayList<Int>
    lateinit var listaAnuncios: ArrayList<Int>
}