package com.routes

import com.routes.controllers.LoginController
import com.routes.controllers.PedidoController
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import mu.KotlinLogging
import org.koin.java.KoinJavaComponent

class LoginRoutes {

    private val logger = KotlinLogging.logger("PedidosRoutes")

    private val controller by KoinJavaComponent.inject<LoginController>(LoginController::class.java)

    fun Application.configureLoginRoutes() {
        routing {
            post("/login") {
                val credentials = call.parameters

                call.respond(controller.login(credentials))
            }
        }
    }
}