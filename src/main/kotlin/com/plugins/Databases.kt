package com.plugins

import com.data.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object Databases {
    fun init() {
        val database = connectToSQLite()
        transaction(database) {
            SchemaUtils.create(MotorHomes, Usuarios, Pedidos, Fabricas, Anuncios, Disponibilidades, Chats, Messages)
        }

        transaction {
            Usuarios.insert {
                it[nome] = "Dailon"
                it[sobrenome] = "Petersen"
                it[email] = "dailon.fernando@mail.com"
                it[password] = "senha"
                it[telefone] = "51995530388"
            }
            Usuarios.insert {
                it[nome] = "Manoela"
                it[sobrenome] = "Espindola"
                it[email] = "manoela.espindola@mail.com"
                it[password] = "senha"
                it[telefone] = "51995530388"
            }
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
}

fun connectToSQLite(): Database {
    return Database.connect("jdbc:sqlite:C:/Desenvolvimento/WebMotorHome/Database/WebMotorHomeHost.db", driver = "org.sqlite.JDBC")
}