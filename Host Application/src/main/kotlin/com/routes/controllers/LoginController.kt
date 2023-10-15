package com.routes.controllers

import com.data.Fabrica
import com.database.dao.implementations.DAOFabricaInterfaceImpl
import com.routes.facades.LoginControllerFacade
import com.routes.facades.UserControllerFacade
import com.security.Encryptor
import io.ktor.http.*
import io.ktor.server.util.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginController : LoginControllerFacade,  KoinComponent {

    private val userController by inject<UserControllerFacade>()
    override suspend fun login(item: Parameters): Boolean {
        val email = item.getOrFail("email")
        val password = item.getOrFail("password")
        val user = userController.getByEmail(email)

        user?.let {
            return Encryptor.isPasswordCorrect(password, user.password)
        } ?: throw NoSuchElementException()
    }
}