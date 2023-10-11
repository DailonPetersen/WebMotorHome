package com.example.webmotorhomeapp.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.webmotorhomeapp.data.factory.Fabrica

@Dao
interface FabricaDAO {

    @Query("SELECT * FROM Fabrica")
    fun getAll(): List<Fabrica>

    @Query("SELECT * FROM Fabrica WHERE id IN (:fabricaIds)")
    fun loadAllByIds(fabricaIds: IntArray): List<Fabrica>

    @Query("SELECT * FROM Fabrica WHERE id LIKE :idFabrica LIMIT 1")
    fun findFabricaById(idFabrica: Int): Fabrica

    @Insert
    fun insertAll(fabricas: List<Fabrica>)

    @Insert
    fun insertFabrica(fabrica: Fabrica)

    @Delete
    fun delete(Fabrica: Fabrica)

}