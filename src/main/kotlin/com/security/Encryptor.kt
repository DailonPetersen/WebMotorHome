package com.security

import at.favre.lib.crypto.bcrypt.BCrypt
import io.ktor.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

val SECRET_KEY = "981129961005"
val ALGORITHM = "HS256"

private val HASH_KEY = hex(SECRET_KEY)
private val HMAC_KEY = SecretKeySpec(HASH_KEY, ALGORITHM)

object Encryptor {
    fun hasher(password: String): String {
        val hmac = Mac.getInstance(ALGORITHM)
        hmac.init(HMAC_KEY)
        return hex(hmac.doFinal(password.toByteArray(Charsets.UTF_8)))
    }

    fun hashPassword(password: String): String {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray()) // 12 is the recommended cost factor
    }

    fun isPasswordCorrect(inputPassword: String, storedPasswordHash: String): Boolean {
        val bcrypt = BCrypt.verifyer()
        return bcrypt.verify(inputPassword.toCharArray(), storedPasswordHash).verified
    }
}
