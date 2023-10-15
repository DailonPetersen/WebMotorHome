package com.routes

import com.data.Message
import com.routes.controllers.MessageController
import com.routes.facades.MessageControllerFacade
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import mu.KotlinLogging
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent


object MessageRoutes: KoinComponent{

    private val logger = KotlinLogging.logger("MessageRoutes")

    private val controller by inject<MessageControllerFacade>()

    fun Application.configureMessageRoutes()  {

        routing {
            get("/messages") {
                call.respond(controller.getAll())
            }

            get ("/messages/{id}") {
                val id = call.parameters.getOrFail("id").toInt()
                logger.debug("id $id id type: ${id.javaClass.simpleName}")
                call.respond(controller.message(id))
            }

            post ("/message") {
                val objectReceived = call.receiveParameters()

                val messageInsert = call.receive<Message>()

                val messageInserted = controller.addNewMessage(messageInsert)
                messageInserted?.let {
                    call.respondRedirect("messages/${it.id}")
                } ?: call.respond(HttpStatusCode.BadGateway)
            }

            put ("/message/{id}") {
                val objectReceived = call.receiveParameters()
                val id = call.parameters.getOrFail("id").toInt()
                if (controller.editMessage(objectReceived, id)) call.respond(HttpStatusCode.OK)
                else call.respond(HttpStatusCode.BadRequest)
            }

            delete ("/message/{id}"){
                val id = call.parameters.getOrFail("id").toInt()

                if (controller.remove(id)) call.respond(HttpStatusCode.OK)
                else call.respond(HttpStatusCode.BadRequest)
            }
        }
    }

}