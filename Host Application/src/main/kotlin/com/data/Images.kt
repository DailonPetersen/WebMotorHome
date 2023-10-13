package com.data

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.statements.api.ExposedBlob

class Image(
    val id: Int? = null,
    val idMotorhome: Int,
    val imageData: ExposedBlob
)

object Images: Table("images") {
    val id = integer("id").autoIncrement().uniqueIndex()
    val idMotorhome = integer("id_motorhome") references MotorHomes.id
    val imageData = blob("image_data")

    override val primaryKey = PrimaryKey(Images.id)
}