package com.routes.motorhome

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
import org.koin.java.KoinJavaComponent.inject
import java.text.DateFormat

object MotorHomeRoutes {
    private val logger = KotlinLogging.logger("MotorhomeRoutes")

    val controller by inject<MotorhomeController>(MotorhomeController::class.java)

    fun Application.configureMotorHomeRoutes()  {
        install(ContentNegotiation) {
            gson {
                setDateFormat(DateFormat.LONG)
                setPrettyPrinting()
                setLenient()
            }
        }
        install(StatusPages) {
            exception<Throwable> { call, cause ->
                call.respondText("500: $cause ", status = HttpStatusCode.InternalServerError)
            }
        }
        routing {
            get("/motorhomes") {
                call.respond(controller.getAll())
            }

            get ("/motorhomes/{id}") {
                val id = call.parameters.getOrFail("id").toInt()
                logger.debug("id $id id type: ${id.javaClass.simpleName}")
                call.respond(controller.motorhome(id))
            }

            post ("/motorhome") {
                val objectReceived = call.receiveParameters()
                val motorHomeInserted = controller.addNewMotorHome(objectReceived)
                motorHomeInserted?.let {
                    call.respondRedirect("motorhomes/${it.id}")
                } ?: call.respond(HttpStatusCode.BadGateway)
            }

            put ("/motorhome/{id}") {
                val objectReceived = call.receiveParameters()
                val id = call.parameters.getOrFail("id").toInt()

                if (controller.editMotorhome(objectReceived, id)) call.respond(HttpStatusCode.OK)
                else call.respond(HttpStatusCode.BadRequest)
            }

            delete ("/motorhome/{id}"){
                val id = call.parameters.getOrFail("id").toInt()

                if (controller.remove(id)) call.respond(HttpStatusCode.OK)
                else call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}