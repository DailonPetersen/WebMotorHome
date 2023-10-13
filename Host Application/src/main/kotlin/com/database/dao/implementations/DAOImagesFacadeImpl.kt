package com.database.dao.implementations

import com.data.Image
import com.data.Images
import com.database.dao.facades.DAOImagesFacade
import com.plugins.DatabaseUtil
import com.plugins.Databases.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DAOImagesFacadeImpl : DAOImagesFacade {

    override suspend fun getAll(): List<Image> = dbQuery {
        Images.selectAll().map { DatabaseUtil.resultRowToImage(it) }
    }

    override suspend fun get(id: Int): Image? = dbQuery {
        Images.select { Images.id eq id as Int }.map { DatabaseUtil.resultRowToImage(it) }.singleOrNull()

    }

    override suspend fun getAllByMotorhome(idMotorHome: Int): List<Image> = dbQuery {
        Images.select { Images.idMotorhome eq idMotorHome as Int }.map { DatabaseUtil.resultRowToImage(it) }
    }

    override suspend fun insert(images: Image): Image? = dbQuery {
        val inserted = Images.insert {
            it[idMotorhome] = images.idMotorhome
            it[imageData] = images.imageData
        }
        inserted.resultedValues?.singleOrNull()?.let { DatabaseUtil.resultRowToImage(it) }
    }
    override suspend fun update(images: Image): Boolean = dbQuery {
        Images.update ({ Images.id eq images.id as Int }) {
            it[idMotorhome] = images.idMotorhome
            it[imageData] = images.imageData
        } > 0
    }

    override suspend fun delete(id: Int): Boolean = dbQuery {
        Images.deleteWhere { Images.id eq id } > 0
    }
}