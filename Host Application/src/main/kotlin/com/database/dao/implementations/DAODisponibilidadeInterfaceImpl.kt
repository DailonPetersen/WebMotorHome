package com.database.dao.implementations

import com.data.Disponibilidade
import com.data.Disponibilidades
import com.data.Fabricas
import com.database.dao.facades.DAODisponibilidadeInterface
import com.plugins.DatabaseUtil
import com.plugins.Databases.dbQuery
import mu.KotlinLogging
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DAODisponibilidadeInterfaceImpl : DAODisponibilidadeInterface {

    private val logger = KotlinLogging.logger("AvaibilityRepository")

    override suspend fun getAll(): List<Disponibilidade> = dbQuery {
        Disponibilidades.selectAll().map{ DatabaseUtil.resultRowToDisponibilidade(it) }
    }

    override suspend fun get(id: Int): Disponibilidade? = dbQuery {
        Fabricas.select { Fabricas.id eq id }
            .map { DatabaseUtil.resultRowToDisponibilidade(it) }
            .singleOrNull()
    }

    override suspend fun insert(disponibilidade: Disponibilidade): Disponibilidade?  = dbQuery {
        val statement = Disponibilidades.insert {
            it[dataInicio] = disponibilidade.dataInicio
            it[dataFim] = disponibilidade.dataFim
        }
        statement.resultedValues?.singleOrNull()?.let{ DatabaseUtil.resultRowToDisponibilidade(it) }
    }

    override suspend fun update(disponibilidade: Disponibilidade): Boolean = dbQuery {
        Disponibilidades.update(where = { Disponibilidades.id eq disponibilidade.id as Int  }) {
            it[dataInicio] = disponibilidade.dataInicio
            it[dataFim] = disponibilidade.dataFim
        } > 0
    }

    override suspend fun delete(id: Int): Boolean = dbQuery {
        Disponibilidades.deleteWhere { Disponibilidades.id eq id } > 0
    }
}