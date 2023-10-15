package com.database.dao.facades

import com.data.Image
import java.sql.Blob

interface DAOImageFacade {
    suspend fun getAllById(id: Int): List<Image>?
    suspend fun insert(image: Image): Image?
}