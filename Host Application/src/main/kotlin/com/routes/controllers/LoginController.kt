package com.routes.controllers

import com.data.Fabrica
import com.database.dao.implementations.DAOFabricaInterfaceImpl
import com.security.isPasswordCorrect
import io.ktor.http.*
import io.ktor.server.util.*
import org.koin.core.component.KoinComponent

class LoginController : KoinComponent {

    private val userController = UserController()

    suspend fun login(item: Parameters): Boolean {
        val email = item.getOrFail("email")
        val password = item.getOrFail("password")
        val user = userController.getByEmail(email)

        user?.let {
            return isPasswordCorrect(password, user.password)
        } ?: throw NoSuchElementException()
    }
}