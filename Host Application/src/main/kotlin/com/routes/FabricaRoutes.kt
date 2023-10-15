package com.routes

import com.data.Fabrica
import com.routes.facades.FabricaControllerFacade
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import mu.KotlinLogging
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object FabricaRoutes: KoinComponent {

    private val logger = KotlinLogging.logger("FabricaRoutes")

    private val controller by inject<FabricaControllerFacade>()

    fun Application.configureFabricaRoutes()  {

        routing {
            get("/fabricas") {
                call.respond(controller.getAll())
            }

            get ("/fabricas/{id}") {
                val id = call.parameters.getOrFail("id").toInt()
                logger.debug("id $id id type: ${id.javaClass.simpleName}")
                call.respond(controller.fabrica(id))
            }

            post ("/fabrica") {
                val objectReceived = call.receiveParameters()

                val fabricaInsert = call.receive<Fabrica>()

                val fabricaInserted = controller.addNewFabrica(fabricaInsert)
                fabricaInserted?.let {
                    call.respondRedirect("fabricas/${it.id}")
                } ?: call.respond(HttpStatusCode.BadGateway)
            }

            put ("/fabrica/{id}") {
                val objectReceived = call.receiveParameters()
                val id = call.parameters.getOrFail("id").toInt()

                if (controller.editFabrica(objectReceived, id)) call.respond(HttpStatusCode.OK)
                else call.respond(HttpStatusCode.BadRequest)
            }

            delete ("/fabrica/{id}"){
                val id = call.parameters.getOrFail("id").toInt()

                if (controller.remove(id)) call.respond(HttpStatusCode.OK)
                else call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
    
}