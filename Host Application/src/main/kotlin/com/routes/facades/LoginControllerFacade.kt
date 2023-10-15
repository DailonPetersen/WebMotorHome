package com.routes.facades

import com.security.Encryptor
import io.ktor.http.*
import io.ktor.server.util.*

interface LoginControllerFacade {

    suspend fun login(item: Parameters): Boolean
}