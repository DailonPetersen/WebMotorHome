package com.routes

import com.data.Image
import com.database.dao.implementations.DAOEnderecoFacadeImpl
import com.database.dao.implementations.DAOImagesFacadeImpl
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.routing.post
import io.ktor.server.util.*
import java.io.File
import java.io.FileInputStream

object ImageRoute {

    val db = DAOImagesFacadeImpl()

    fun Application.configureImageRoute() {

        routing {
            staticResources("/static", "assets")
            post("image/file/{id}") {
                val imageObject = call.receive<Image>()

                val resul = db.insert(imageObject)

                val multipart = call.receiveMultipart()
                val idMotorhome = call.parameters.getOrFail("id").toInt()
                multipart.forEachPart { part ->
                    when(part) {
                        is PartData.FormItem -> Unit
                        is PartData.FileItem -> {
                            if (part.name == "image") {
                                //db.insert(Image(idMotorhome, part))
                                part.save("build/resources/main/static/images/", "$idMotorhome/myImage.jpg")
                            }
                        }
                        else -> Unit
                    }

                }
            }

            get("image/{id}") {
                val fis = FileInputStream("build/resources/main/static/images/")
            }
        }

    }
}


fun PartData.FileItem.save(path: String, fileName: String): String {
    val fileBytes = streamProvider().readBytes()
    val folder = File(path)
    folder.mkdirs()
    File("$path$fileName").writeBytes(fileBytes)
    return fileName
}