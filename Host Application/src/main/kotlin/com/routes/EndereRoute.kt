package com.routes

import com.data.Endereco
import com.data.Image
import com.database.dao.implementations.DAOEnderecoFacadeImpl
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.routing.post
import io.ktor.server.util.*
import java.io.File
import java.io.FileInputStream

object EndereRoute {

    val db = DAOEnderecoFacadeImpl()

    fun Application.configureEnderecoRoute() {

        routing {
//            get("/enderecos") {
//                call.respond(db.getAllById())
//            }

            get ("/enderecos/{id}") {
                val id = call.parameters.getOrFail("id").toInt()

                call.respond(db.get(id)!!)
            }

            post ("/endereco") {
                val objectReceived = call.receiveParameters()

                val enderecoInsert = call.receive<Endereco>()

                val enderecoInserted = db.insert(enderecoInsert)
                enderecoInserted?.let {
                    call.respondRedirect("enderecos/${it.id}")
                } ?: call.respond(HttpStatusCode.BadGateway)
            }
        }

    }
}
