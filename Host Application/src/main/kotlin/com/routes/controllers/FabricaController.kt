package com.routes.controllers

import com.data.Fabrica
import com.database.dao.implementations.DAOFabricaInterfaceImpl
import io.ktor.http.*
import io.ktor.server.util.*
import org.koin.core.component.KoinComponent

class FabricaController: KoinComponent {
    
    private val dbFabrica = DAOFabricaInterfaceImpl()

    suspend fun getAll(): List<Fabrica> = dbFabrica.getAll()

    suspend fun fabrica(id: Int): Fabrica = dbFabrica.get(id) ?: throw NoSuchElementException()

    suspend fun editFabrica(item: Parameters, id: Int): Boolean {
        val fabricaToEdit = Fabrica(
            idUser = item.getOrFail("idUser").toInt(),
            nomeFantasia = item.getOrFail("nomeFantasia"),
            razaoSocial = item.getOrFail("razaoSocial"),
            email = item.getOrFail("email"),
            cnpj = item.getOrFail("cnpj")
        )

        if (fabricaToEdit.idUser == 0) return false
        if (fabricaToEdit.cnpj.isEmpty() || fabricaToEdit.cnpj.isBlank()) return false

        return dbFabrica.update(fabricaToEdit)
    }

    suspend fun addNewFabrica(item: Fabrica): Fabrica? {

        if (item.idUser == 0) return null
        if (item.cnpj.isEmpty() || item.cnpj.isBlank()) return null

        return dbFabrica.insert(item)
    }

    suspend fun remove(id: Int): Boolean = dbFabrica.delete(id)
}