package com.database.dao.implementations

import com.data.*
import com.database.dao.facades.DAOImageFacade
import com.plugins.DatabaseUtil
import com.plugins.Databases
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class DAOEnderecoFacadeImpl{


    suspend fun get(id: Int): Endereco? = Databases.dbQuery {
        Enderecos.select { Enderecos.id eq id }
            .map { DatabaseUtil.resultRowToEnderedo(it) }
            .singleOrNull()
    }

    suspend fun insert(endereco: Endereco): Endereco? = Databases.dbQuery {
        val statement = Enderecos.insert {
            it[idUser] = endereco.idUser
            it[logradouro] = endereco.logradouro
            it[cep] = endereco.cep
            it[bairro] = endereco.bairro
            it[numero] = endereco.numero
            it[cidade] = endereco.cidade
        }
        statement.resultedValues?.singleOrNull()?.let { DatabaseUtil.resultRowToEnderedo(it) }
    }
}