package com

import com.di.ModulesKoinInjection
import com.plugins.*
import com.routes.AnuncioRoutes.configureAnuncioRoutes
import com.routes.ChatRoutes.configureChatRoutes
import com.routes.DisponibilidadeRoutes.configureDisponibilidadeRoutes
import com.routes.EndereRoute.configureEnderecoRoute
import com.routes.FabricaRoutes.configureFabricaRoutes
import com.routes.MessageRoutes.configureMessageRoutes
import com.routes.MotorHomeRoutes.configureMotorHomeRoutes
import com.routes.PedidoRoutes.configurePedidoRoutes
import com.routes.UserRoutes.configureUserRoutes
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.ktor.plugin.Koin
import org.koin.ktor.plugin.KoinApplicationStarted
import org.koin.ktor.plugin.KoinApplicationStopPreparing
import org.koin.ktor.plugin.KoinApplicationStopped

fun main(args: Array<String>) {
    embeddedServer(Netty, port = 5050, host = "127.0.0.1", module = Application::function)
        .start(wait = true)
}

fun Application.function() {
    Databases.init()
    //ModulesKoinInjection.init()
    install(Koin) {
        modules(ModulesKoinInjection.koinModulesFacadesImpl, ModulesKoinInjection.koinModulesController)
    }
    //configureSwagger()
    configureMotorHomeRoutes()
    configureUserRoutes()
    configureFabricaRoutes()
    configureAnuncioRoutes()
    configureChatRoutes()
    configureMessageRoutes()
    configureEnderecoRoute()
    configureDisponibilidadeRoutes()
    configurePedidoRoutes()

    environment.monitor.subscribe(KoinApplicationStarted) {
        log.info("Koin started.")
    }

    environment.monitor.subscribe(KoinApplicationStopPreparing) {
        log.info("Koin stopping...")
    }

    environment.monitor.subscribe(KoinApplicationStopped) {
        log.info("Koin stopped.")
    }
}
