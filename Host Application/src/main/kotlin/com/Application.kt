package com

import com.di.ModulesKoinInjection
import com.plugins.*
import com.routes.MotorHomeRoutes.configureMotorHomeRoutes
import com.routes.AnuncioRoutes.configureAnuncioRoutes
import com.routes.ChatRoutes.configureChatRoutes
import com.routes.MessageRoutes.configureMessageRoutes
import com.routes.UserRoutes.configureUserRoutes
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    embeddedServer(Netty, port = 5050, host = "127.0.0.1", module = Application::function)
        .start(wait = true)
}

fun Application.function() {
    Databases.init()
    ModulesKoinInjection.init()
    //configureSwagger()
    configureMotorHomeRoutes()
    configureUserRoutes()
    configureAnuncioRoutes()
    configureChatRoutes()
    configureMessageRoutes()
}
