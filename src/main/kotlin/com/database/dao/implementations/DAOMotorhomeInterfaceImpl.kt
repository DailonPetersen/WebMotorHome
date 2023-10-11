package com.database.dao.implementations

import com.data.MotorHome
import com.data.MotorHomes
import com.database.dao.facades.DAOMotorHomeFacade
import com.plugins.Databases.dbQuery
import mu.KotlinLogging
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DAOMotorhomeInterfaceImpl : DAOMotorHomeFacade {
    private val logger = KotlinLogging.logger("MotorhomeRepository")

    private fun resultRowToMotorhome(row: ResultRow) = MotorHome(
        id = row[MotorHomes.id],
        modelo = row[MotorHomes.modelo],
        descricao = row[MotorHomes.descricao],
        ano = row[MotorHomes.ano],
        exposicao = row[MotorHomes.exposicao],
        avaliacao = row[MotorHomes.avaliacao]
    )

    override suspend fun getAll(): List<MotorHome> = dbQuery {
        MotorHomes.selectAll().map(::resultRowToMotorhome)
    }

    override suspend fun get(id: Int): MotorHome? {
        try {
            val resultado = dbQuery {
                MotorHomes.select { MotorHomes.id eq id }
                    .map(::resultRowToMotorhome)
                    .singleOrNull()
            }
            return resultado
        } catch (e: Exception) {
            logger.error("Error querying motorhome by ID: $id", e)
            throw e
        }

    }

    override suspend fun insert(motorHome: MotorHome) = dbQuery {
        val insertStament = MotorHomes.insert {
            it[modelo] = motorHome.modelo
            it[descricao] = motorHome.descricao
            it[ano] = motorHome.ano
            it[exposicao] = motorHome.exposicao
            it[avaliacao] = motorHome.avaliacao
        }
        insertStament.resultedValues?.singleOrNull()?.let(::resultRowToMotorhome)
    }

    override suspend fun update(motorHome: MotorHome): Boolean = dbQuery {
        try {
            MotorHomes.update( { MotorHomes.id eq motorHome.id as Int } ) {
                it[modelo] = motorHome.modelo
                it[ano] = motorHome.ano
                it[descricao] = motorHome.descricao
                it[avaliacao] = motorHome.avaliacao
                it[exposicao] = motorHome.exposicao
            } > 0
        } catch (e: Exception) {
            logger.error("Error querying motorhome by ID: ${motorHome.id}", e)
            throw e
        }
    }

    override suspend fun delete(id: Int): Boolean = dbQuery {
        MotorHomes.deleteWhere { MotorHomes.id eq id } > 0
    }
}
