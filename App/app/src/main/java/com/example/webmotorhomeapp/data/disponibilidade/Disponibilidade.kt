package com.example.webmotorhomeapp.data.disponibilidade

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Disponibilidade(
    @PrimaryKey val id: Int? = null,
    @ColumnInfo val dataInicio: LocalDate,
    @ColumnInfo val dataFim: LocalDate
)