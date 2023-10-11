package com.routes

import com.data.Anuncio
import com.routes.controllers.AnuncioController
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import mu.KotlinLogging
import org.koin.java.KoinJavaComponent
import java.text.DateFormat

object AnuncioRoutes {

    private val logger = KotlinLogging.logger("AnunciosRoutes")

    private val controller by KoinJavaComponent.inject<AnuncioController>(AnuncioController::class.java)

    fun Application.configureAnuncioRoutes()  {

        routing {
            get("/anuncios") {
                call.respond(controller.getAll())
            }

            get ("/anuncios/{id}") {
                val id = call.parameters.getOrFail("id").toInt()
                logger.debug("id $id id type: ${id.javaClass.simpleName}")
                call.respond(controller.anuncio(id))
            }

            post ("/anuncio") {
                val objectReceived = call.receiveParameters()

                val anuncioInsert = call.receive<Anuncio>()

                val anuncioInserted = controller.addNewAnuncio(anuncioInsert)
                anuncioInserted?.let {
                    call.respondRedirect("anuncios/${it.id}")
                } ?: call.respond(HttpStatusCode.BadGateway)
            }

            put ("/anuncio/{id}") {
                val objectReceived = call.receiveParameters()
                val id = call.parameters.getOrFail("id").toInt()

                if (controller.editAnuncio(objectReceived, id)) call.respond(HttpStatusCode.OK)
                else call.respond(HttpStatusCode.BadRequest)
            }

            delete ("/anuncio/{id}"){
                val id = call.parameters.getOrFail("id").toInt()

                if (controller.remove(id)) call.respond(HttpStatusCode.OK)
                else call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}