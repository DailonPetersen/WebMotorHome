package com.example.webmotorhomeapp.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.webmotorhomeapp.data.disponibilidade.Disponibilidade

@Dao
interface DisponibilidadeDAO {

    @Query("SELECT * FROM Disponibilidade")
    fun getAll(): List<Disponibilidade>

    @Query("SELECT * FROM Disponibilidade WHERE id IN (:disponibilidadeIds)")
    fun loadAllByIds(disponibilidadeIds: IntArray): List<Disponibilidade>

    @Query("SELECT * FROM Disponibilidade WHERE id LIKE :idDisponibilidade LIMIT 1")
    fun findDisponibilidadeById(idDisponibilidade: Int): Disponibilidade

    @Insert
    fun insertAll(disponibilidades: List<Disponibilidade>)

    @Insert
    fun insertDisponibilidade(disponibilidade: Disponibilidade)

    @Delete
    fun delete(Disponibilidade: Disponibilidade)

}