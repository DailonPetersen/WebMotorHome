package com.routes.facades

import com.data.Usuario
import io.ktor.http.*
import io.ktor.server.util.*

interface UserControllerFacade {

    suspend fun getAll(): List<Usuario>
    suspend fun getByEmail(email: String): Usuario?
    suspend fun user(id: Int): Usuario
    suspend fun editUser(item: Parameters, id: Int): Boolean
    suspend fun addNewUser(item: Parameters): Usuario?
    suspend fun remove(id: Int): Boolean
}