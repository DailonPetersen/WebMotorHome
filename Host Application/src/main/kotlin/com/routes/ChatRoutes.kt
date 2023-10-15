package com.routes

import com.data.Chat
import com.routes.facades.ChatControllerFacade
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import mu.KotlinLogging
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object ChatRoutes: KoinComponent{

    private val logger = KotlinLogging.logger("ChatsRoutes")

    private val controller by inject<ChatControllerFacade>()

    fun Application.configureChatRoutes()  {

        routing {
            get("/chats") {
                call.respond(controller.getAll())
            }

            get ("/chats/{id}") {
                val id = call.parameters.getOrFail("id").toInt()
                logger.debug("id $id id type: ${id.javaClass.simpleName}")
                call.respond(controller.chat(id))
            }

            post ("/chat") {
                val objectReceived = call.receiveParameters()

                val chatInsert = call.receive<Chat>()

                val chatInserted = controller.addNewChat(chatInsert)
                chatInserted?.let {
                    call.respondRedirect("chats/${it.id}")
                } ?: call.respond(HttpStatusCode.BadGateway)
            }

            put ("/chat/{id}") {
                val objectReceived = call.receiveParameters()
                val id = call.parameters.getOrFail("id").toInt()
                if (controller.editChat(objectReceived, id)) call.respond(HttpStatusCode.OK)
                else call.respond(HttpStatusCode.BadRequest)
            }

            delete ("/chat/{id}"){
                val id = call.parameters.getOrFail("id").toInt()

                if (controller.remove(id)) call.respond(HttpStatusCode.OK)
                else call.respond(HttpStatusCode.BadRequest)
            }
        }
    }

}