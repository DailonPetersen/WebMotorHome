package com

import com.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    embeddedServer(Netty, port = 5050, host = "127.0.0.1", module = Application::function)
        .start(wait = true)
}

fun Application.function() {
    configureSerialization()
    configureDatabases()
    configureSwagger()
    //configureSecurity()
    configureRouting()
}
