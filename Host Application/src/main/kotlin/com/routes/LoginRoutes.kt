package com.routes

import com.routes.controllers.LoginController
import com.routes.controllers.PedidoController
import com.routes.controllers.UserController
import com.routes.facades.LoginControllerFacade
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import mu.KotlinLogging
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent

class LoginRoutes: KoinComponent {

    private val logger = KotlinLogging.logger("PedidosRoutes")

    private val controller by inject<LoginControllerFacade>()

    fun Application.configureLoginRoutes() {
        routing {
            post("/login") {
                val credentials = call.parameters

                call.respond(controller.login(credentials))
            }
        }
    }
}