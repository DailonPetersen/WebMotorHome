package com.routes

import com.data.Disponibilidade
import com.routes.controllers.DisponibilidadeController
import com.routes.facades.DisponibilidadeControllerFacade
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import mu.KotlinLogging
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object DisponibilidadeRoutes: KoinComponent {

    private val logger = KotlinLogging.logger("DisponibilidadesRoutes")

    private val controller by inject<DisponibilidadeControllerFacade>()

    fun Application.configureDisponibilidadeRoutes()  {

        routing {
            get("/disponibilidades") {
                call.respond(controller.getAll())
            }

            get ("/disponibilidades/{id}") {
                val id = call.parameters.getOrFail("id").toInt()
                logger.debug("id $id id type: ${id.javaClass.simpleName}")
                call.respond(controller.disponibilidade(id))
            }

            post ("/disponibilidade") {
                val objectReceived = call.receiveParameters()

                val disponibilidadeInsert = call.receive<Disponibilidade>()

                val disponibilidadeInserted = controller.addNewDisponibilidade(disponibilidadeInsert)
                disponibilidadeInserted?.let {
                    call.respondRedirect("disponibilidades/${it.id}")
                } ?: call.respond(HttpStatusCode.BadGateway)
            }

            put ("/disponibilidade/{id}") {
                val objectReceived = call.receive<Disponibilidade>()
                
                if (controller.editDisponibilidade(objectReceived)) call.respond(HttpStatusCode.OK)
                else call.respond(HttpStatusCode.BadRequest)
            }

            delete ("/disponibilidade/{id}"){
                val id = call.parameters.getOrFail("id").toInt()

                if (controller.remove(id)) call.respond(HttpStatusCode.OK)
                else call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
    
}