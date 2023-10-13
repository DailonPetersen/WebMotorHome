package com.database.dao.implementations

import at.favre.lib.crypto.bcrypt.BCrypt
import com.data.Usuario
import com.data.Usuarios
import com.database.dao.facades.DAOUsuarioFacade
import com.plugins.DatabaseUtil
import com.plugins.Databases.dbQuery
import mu.KotlinLogging
import com.security.Encryptor.hashPassword
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.security.SecureRandom
import java.util.*

class DAOUserFacadeImpl: DAOUsuarioFacade {

    private val logger = KotlinLogging.logger("UserRepository")

    override suspend fun getAll(): List<Usuario> = dbQuery {
        Usuarios.selectAll().map { DatabaseUtil.resultRowToUser(it) }
    }

    override suspend fun get(id: Int): Usuario? = dbQuery {
        Usuarios.select { Usuarios.id eq id}
            .map { DatabaseUtil.resultRowToUser(it) }
            .singleOrNull()
    }

    override suspend fun getByEmail(email: String): Usuario? = dbQuery {
        Usuarios.select { Usuarios.email eq email }
            .map { DatabaseUtil.resultRowToUser(it) }
            .singleOrNull()
    }

    override suspend fun insert(usuario: Usuario): Usuario? = dbQuery {
        val statementInsert = Usuarios.insert {
            it[nome] = usuario.nome
            it[sobrenome] = usuario.sobrenome
            it[email] = usuario.email
            it[password] = hashPassword(usuario.password)
            it[telefone] = usuario.telefone
        }
        statementInsert.resultedValues?.singleOrNull()?.let{ DatabaseUtil.resultRowToUser(it) }
    }

    override suspend fun update(usuario: Usuario): Boolean = dbQuery {
        Usuarios.update(where = { Usuarios.id eq usuario.id as Int}) {
            it[nome] = usuario.nome
            it[sobrenome] = usuario.sobrenome
            it[email] = usuario.email
            it[password] = usuario.password
            it[telefone] = usuario.telefone
        } > 0
    }

    override suspend fun delete(id: Int): Boolean = dbQuery {
        Usuarios.deleteWhere { Usuarios.id eq id } > 0
    }
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