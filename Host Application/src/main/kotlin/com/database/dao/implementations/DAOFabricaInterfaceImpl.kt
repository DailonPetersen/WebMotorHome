package com.database.dao.implementations

import com.data.Fabrica
import com.data.Fabricas
import com.data.Usuarios
import com.database.dao.facades.DAOFabricaInterface
import com.plugins.DatabaseUtil
import com.plugins.Databases.dbQuery
import mu.KotlinLogging
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DAOFabricaInterfaceImpl : DAOFabricaInterface {

    private val logger = KotlinLogging.logger("FactoryRepository")

    override suspend fun getAll(): List<Fabrica> = dbQuery {
        Fabricas.selectAll().map{ DatabaseUtil.resultRowToFabrica(it) }
    }

    override suspend fun get(id: Int): Fabrica? = dbQuery {
        Fabricas.select { Fabricas.id eq id }
            .map { DatabaseUtil.resultRowToFabrica(it) }
            .singleOrNull()
    }

    override suspend fun insert(fabrica: Fabrica): Fabrica? = dbQuery {
        val statement = Fabricas.insert {
            it[idUser] = fabrica.idUser
            it[nomeFantasia] = fabrica.nomeFantasia
            it[razaoSocial] = fabrica.razaoSocial
            it[email] = fabrica.email
            it[telefone] = fabrica.telefone
            it[cnpj] = fabrica.cnpj
        }
        statement.resultedValues?.singleOrNull()?.let { DatabaseUtil.resultRowToFabrica(it) }
    }

    override suspend fun update(fabrica: Fabrica): Boolean = dbQuery {
        Fabricas.update(where = { Fabricas.id eq fabrica.id as Int }) {
            it[idUser] = fabrica.idUser
            it[nomeFantasia] = fabrica.nomeFantasia
            it[razaoSocial] = fabrica.razaoSocial
            it[email] = fabrica.email
            it[telefone] = fabrica.telefone
            it[cnpj] = fabrica.cnpj
        } > 0
    }

    override suspend fun delete(id: Int): Boolean = dbQuery {
        Fabricas.deleteWhere { Fabricas.id eq id } > 0
    }
}