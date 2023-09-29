package com.routes.controllers

import com.data.Usuario
import com.database.dao.implementations.DAOUserFacadeImpl
import io.ktor.http.*
import io.ktor.server.util.*
import org.koin.core.component.KoinComponent

class UserController: KoinComponent {

    private val dbUser = DAOUserFacadeImpl()

    suspend fun getAll(): List<Usuario> = dbUser.getAll()

    suspend fun user(id: Int): Usuario = dbUser.get(id) ?: throw NoSuchElementException()

    suspend fun editUser(item: Parameters, id: Int): Boolean {
        val userToEdit = Usuario(
            nome = item.getOrFail("nome"),
            sobrenome = item.getOrFail("sobrenome"),
            email = item.getOrFail("email"),
            telefone = item.getOrFail("telefone")
        )

        if (userToEdit.id != id) return false
        if (userToEdit.nome.length > 50) return false
        if (userToEdit.sobrenome.length > 70) return false
        if (userToEdit.email.isBlank() || userToEdit.email.isEmpty()) return false

        return dbUser.update(userToEdit)
    }

    suspend fun addNewUser(item: Parameters): Usuario? {
        val userToAdd = Usuario(
            nome = item.getOrFail("nome"),
            sobrenome = item.getOrFail("sobrenome"),
            email = item.getOrFail("email"),
            telefone = item.getOrFail("telefone")
        )

        if (userToAdd.nome.length > 50) return null
        if (userToAdd.sobrenome.length > 70) return null
        if (userToAdd.email.isBlank() || userToAdd.email.isEmpty()) return null

        return dbUser.insert(userToAdd)
    }

    suspend fun remove(id: Int): Boolean = dbUser.delete(id)
}