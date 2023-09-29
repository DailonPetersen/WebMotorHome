package com.database.dao.implementations

import com.data.Fabrica
import com.data.Fabricas
import com.data.Usuarios
import com.database.dao.facades.DAOFabricaInterface
import com.plugins.Databases.dbQuery
import mu.KotlinLogging
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DAOFabricaInterfaceImpl : DAOFabricaInterface {

    private val logger = KotlinLogging.logger("FactoryRepository")

    override suspend fun getAll(): List<Fabrica> = dbQuery {
        Fabricas.selectAll().map(::resultRowToFabrica)
    }

    override suspend fun get(id: Int): Fabrica? = dbQuery {
        Fabricas.select { Fabricas.id eq id }
            .map (::resultRowToFabrica)
            .singleOrNull()
    }

    override suspend fun insert(fabrica: Fabrica): Fabrica? = dbQuery {
        val statement = Fabricas.insert {
            it[idUser] = fabrica.idUser
            it[nomeFantasia] = fabrica.nomeFantasia
            it[razaoSocial] = fabrica.razaoSocial
            it[email] = fabrica.email
            it[cnpj] = fabrica.cnpj
        }
        statement.resultedValues?.singleOrNull()?.let(::resultRowToFabrica)
    }

    override suspend fun update(fabrica: Fabrica): Boolean = dbQuery {
        Fabricas.update(where = { Fabricas.id eq fabrica.id}) {
            it[idUser] = fabrica.idUser
            it[nomeFantasia] = fabrica.nomeFantasia
            it[razaoSocial] = fabrica.razaoSocial
            it[email] = fabrica.email
            it[cnpj] = fabrica.cnpj
        } > 0
    }

    override suspend fun delete(id: Int): Boolean = dbQuery {
        Fabricas.deleteWhere { Fabricas.id eq id } > 0
    }

    private fun resultRowToFabrica(row: ResultRow) = Fabrica(
        id = row[Fabricas.id],
        idUser = row[Fabricas.idUser],
        nomeFantasia = row[Fabricas.nomeFantasia],
        razaoSocial = row[Fabricas.razaoSocial],
        email = row[Fabricas.email],
        cnpj = row[Fabricas.cnpj]
    )
}