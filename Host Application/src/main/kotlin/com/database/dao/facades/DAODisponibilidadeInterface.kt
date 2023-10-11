package com.database.dao.facades

import com.data.Anuncio
import com.data.Disponibilidade

interface DAODisponibilidadeInterface {
    suspend fun getAll(): List<Disponibilidade>
    suspend fun get(id: Int): Disponibilidade?
    suspend fun insert(disponibilidade: Disponibilidade): Disponibilidade?
    suspend fun update(disponibilidade: Disponibilidade): Boolean
    suspend fun delete(id: Int): Boolean
}