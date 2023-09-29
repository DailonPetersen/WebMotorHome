package com.database.dao.implementations

import com.data.MotorHomes
import com.data.Usuario
import com.data.Usuarios
import com.plugins.Databases.dbQuery
import mu.KotlinLogging
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import at.favre.lib.crypto.bcrypt.BCrypt
import com.database.dao.facades.DAOUsuarioFacade
import java.security.SecureRandom
import java.util.Base64

class DAOUserFacadeImpl: DAOUsuarioFacade {

    private val logger = KotlinLogging.logger("UserRepository")

    override suspend fun getAll(): List<Usuario> = dbQuery {
        MotorHomes.selectAll().map { resultRowToUser(it) }
    }

    override suspend fun get(id: Int): Usuario? = dbQuery {
        Usuarios.select { Usuarios.id eq id}
            .map { resultRowToUser(it) }
            .singleOrNull()
    }

    override suspend fun insert(usuario: Usuario): Usuario? = dbQuery {
        val statementInsert = Usuarios.insert {
            it[nome] = usuario.nome
            it[sobrenome] = usuario.sobrenome
            it[email] = usuario.email
            it[telefone] = usuario.telefone
        }
        statementInsert.resultedValues?.singleOrNull()?.let(::resultRowToUser)
    }

    override suspend fun update(usuario: Usuario): Boolean = dbQuery {
        Usuarios.update(where = { Usuarios.id eq usuario.id as Int}) {
            it[nome] = usuario.nome
            it[sobrenome] = usuario.sobrenome
            it[email] = usuario.email
            it[telefone] = usuario.telefone
        } > 0
    }

    override suspend fun delete(id: Int): Boolean = dbQuery {
        Usuarios.deleteWhere { Usuarios.id eq id } > 0
    }

    private fun resultRowToUser(row: ResultRow) = Usuario(
        id = row[Usuarios.id],
        nome = row[Usuarios.nome],
        sobrenome = row[Usuarios.sobrenome],
        email = row[Usuarios.email],
        telefone = row[Usuarios.telefone]
    )
}

class Cripty() {

    private fun generateSalt(): String {
        val random = SecureRandom()
        val salt = ByteArray(16)
        random.nextBytes(salt)
        return Base64.getEncoder().encodeToString(salt)
    }

    fun hashPassword(password: String): String {
        return BCrypt.withDefaults().hash(12, generateSalt().toByteArray(), password.toByteArray()).toString()
    }

    fun isPasswordCorrect(inputPassword: String, storedPasswordHash: String): Boolean {
        val verifyer = BCrypt.verifyer()
        return verifyer.verify(inputPassword.toCharArray(), storedPasswordHash.toCharArray()).verified
    }
}