package com.routes

import com.routes.controllers.MotorhomeController
import com.routes.controllers.UserController
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

object UserRoutes {

    private val logger = KotlinLogging.logger("UserRoutes")

    private val controller = UserController()

    fun Application.configureUserRoutes() {

        routing {
            route("/users") {
                get("/list") {
                    call.respond(controller.getAll())
                }

                get ("/{id}") {
                    val id = call.parameters.getOrFail("id").toInt()
                    logger.debug("id $id id type: ${id.javaClass.simpleName}")
                    call.respond(controller.user(id))
                }

                post ("/user") {
                    val objectReceived = call.receiveParameters()
                    val userInserted = controller.addNewUser(objectReceived)
                    userInserted?.let {
                        call.respondRedirect("us  ers/${it.id}")
                    } ?: call.respond(HttpStatusCode.BadGateway)
                }

                put ("/user/list/{id}") {
                    val objectReceived = call.receiveParameters()
                    val id = call.parameters.getOrFail("id").toInt()

                    if (controller.editUser(objectReceived, id)) call.respond(HttpStatusCode.OK)
                    else call.respond(HttpStatusCode.BadRequest)
                }

                delete ("/user/{id}"){
                    val id = call.parameters.getOrFail("id").toInt()

                    if (controller.remove(id)) call.respond(HttpStatusCode.OK)
                    else call.respond(HttpStatusCode.BadRequest)
                }
            }

        }
    }
}