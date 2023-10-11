package com.database.dao.facades

import com.data.Disponibilidade
import com.data.Fabrica

interface DAOFabricaInterface {
    suspend fun getAll(): List<Fabrica>
    suspend fun get(id: Int): Fabrica?
    suspend fun insert(fabrica: Fabrica): Fabrica?
    suspend fun update(fabrica: Fabrica): Boolean
    suspend fun delete(id: Int): Boolean
}