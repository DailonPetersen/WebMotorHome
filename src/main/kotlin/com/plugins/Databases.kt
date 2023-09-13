package com.plugins

import com.data.MotorHomes
import com.data.TempTable
import io.ktor.server.application.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object Databases {
    fun init() {
        val database = connectToSQLite()
        transaction(database) {
            SchemaUtils.create(MotorHomes)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
}

fun connectToSQLite(): Database {
    return Database.connect("jdbc:sqlite:C:/Desenvolvimento/WebMotorHome/Database/WebMotorHomeHost.db", driver = "org.sqlite.JDBC")
}