package com.routes

import com.data.Pedido
import com.routes.controllers.PedidoController
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import mu.KotlinLogging
import org.koin.java.KoinJavaComponent

class PedidoRoutes {

    private val logger = KotlinLogging.logger("PedidosRoutes")

    private val controller by KoinJavaComponent.inject<PedidoController>(PedidoController::class.java)

    fun Application.configurePedidoRoutes()  {

        routing {
            get("/pedidos") {
                call.respond(controller.getAll())
            }

            get ("/pedidos/{id}") {
                val id = call.parameters.getOrFail("id").toInt()
                logger.debug("id $id id type: ${id.javaClass.simpleName}")
                call.respond(controller.pedido(id))
            }

            post ("/pedido") {
                val objectReceived = call.receiveParameters()

                val pedidoInsert = call.receive<Pedido>()

                val pedidoInserted = controller.addNewPedido(pedidoInsert)
                pedidoInserted?.let {
                    call.respondRedirect("pedidos/${it.id}")
                } ?: call.respond(HttpStatusCode.BadGateway)
            }

            put ("/pedido/{id}") {
                val objectReceived = call.receiveParameters()
                val id = call.parameters.getOrFail("id").toInt()

                if (controller.editPedido(objectReceived, id)) call.respond(HttpStatusCode.OK)
                else call.respond(HttpStatusCode.BadRequest)
            }

            delete ("/pedido/{id}"){
                val id = call.parameters.getOrFail("id").toInt()

                if (controller.remove(id)) call.respond(HttpStatusCode.OK)
                else call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}