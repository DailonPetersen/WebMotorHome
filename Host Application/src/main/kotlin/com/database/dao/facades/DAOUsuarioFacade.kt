package com.database.dao.facades

import com.data.MotorHome
import com.data.Usuario

interface DAOUsuarioFacade {
    suspend fun getAll(): List<Usuario>
    suspend fun get(id: Int): Usuario?
    suspend fun getByEmail(email: String): Usuario?

    suspend fun insert(usuario: Usuario): Usuario?
    suspend fun update(usuario: Usuario): Boolean
    suspend fun delete(id: Int): Boolean
}