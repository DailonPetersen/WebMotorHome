package com.routes.controllers

import com.data.Usuario
import com.database.dao.facades.DAOUsuarioFacade
import com.database.dao.implementations.DAOUserFacadeImpl
import com.routes.facades.UserControllerFacade
import io.ktor.http.*
import io.ktor.server.util.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserController: UserControllerFacade, KoinComponent {

    private val dbUser by inject<DAOUsuarioFacade>()

    override suspend fun getAll(): List<Usuario> = dbUser.getAll()

    override suspend fun getByEmail(email: String): Usuario? = dbUser.getByEmail(email)

    override suspend fun user(id: Int): Usuario = dbUser.get(id) ?: throw NoSuchElementException()

    override suspend fun editUser(item: Parameters, id: Int): Boolean {
        val userToEdit = Usuario(
            nome = item.getOrFail("nome"),
            sobrenome = item.getOrFail("sobrenome"),
            email = item.getOrFail("email"),
            password = item.getOrFail("password"),
            telefone = item.getOrFail("telefone")
        )

        if (userToEdit.id != id) return false
        if (userToEdit.nome.length > 50) return false
        if (userToEdit.sobrenome.length > 70) return false
        if (userToEdit.email.isBlank() || userToEdit.email.isEmpty()) return false

        return dbUser.update(userToEdit)
    }

    override suspend fun addNewUser(item: Parameters): Usuario? {
        val userToAdd = Usuario(
            nome = item.getOrFail("nome"),
            sobrenome = item.getOrFail("sobrenome"),
            email = item.getOrFail("email"),
            password = item.getOrFail("password"),
            telefone = item.getOrFail("telefone")
        )

        if (userToAdd.nome.length > 50) return null
        if (userToAdd.sobrenome.length > 70) return null
        if (userToAdd.email.isBlank() || userToAdd.email.isEmpty()) return null

        return dbUser.insert(userToAdd)
    }

    override suspend fun remove(id: Int): Boolean = dbUser.delete(id)
}