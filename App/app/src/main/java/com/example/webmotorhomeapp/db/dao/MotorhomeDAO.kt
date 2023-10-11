package com.example.webmotorhomeapp.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.webmotorhomeapp.data.motorhome.Motorhome


interface MotorhomeDAO {

    @Query("SELECT * FROM Motorhome")
    fun getAll(): List<Motorhome>

    @Query("SELECT * FROM Motorhome WHERE id IN (:motorhome)")
    fun loadAllByIds(motorhome:  IntArray): List<Motorhome>

    @Query("SELECT * FROM Motorhome WHERE id LIKE :id LIMIT 1")
    fun findMotorhomeById(id: Int): Motorhome

    @Insert
    fun insertAll(motorhome: List<Motorhome>)

    @Insert
    fun insertMotorhome(motorhome: Motorhome)

    @Delete
    fun delete(motorhome: Motorhome)
}