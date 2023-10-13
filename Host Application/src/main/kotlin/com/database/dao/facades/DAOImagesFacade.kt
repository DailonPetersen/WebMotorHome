package com.database.dao.facades

import com.data.Image

interface DAOImagesFacade {
    suspend fun getAll(): List<Image>
    suspend fun get(id: Int): Image?
    suspend fun getAllByMotorhome(idMotorHome: Int): List<Image>?
    suspend fun insert(images: Image): Image?
    suspend fun update(images: Image): Boolean
    suspend fun delete(id: Int): Boolean
}