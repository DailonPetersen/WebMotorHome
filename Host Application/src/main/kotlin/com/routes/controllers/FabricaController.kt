package com.routes.controllers

import com.data.Fabrica
import com.database.dao.facades.DAOFabricaInterface
import com.database.dao.implementations.DAOFabricaInterfaceImpl
import com.database.dao.implementations.DAOUserFacadeImpl
import com.routes.facades.FabricaControllerFacade
import io.ktor.http.*
import io.ktor.server.util.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FabricaController: FabricaControllerFacade, KoinComponent {
    
    private val dbFabrica by inject<DAOFabricaInterface>()

    override suspend fun getAll(): List<Fabrica> = dbFabrica.getAll()

    override suspend fun fabrica(id: Int): Fabrica = dbFabrica.get(id) ?: throw NoSuchElementException()

    override suspend fun editFabrica(item: Parameters, id: Int): Boolean {
        val fabricaToEdit = Fabrica(
            idUser = item.getOrFail("idUser").toInt(),
            nomeFantasia = item.getOrFail("nomeFantasia"),
            razaoSocial = item.getOrFail("razaoSocial"),
            email = item.getOrFail("email"),
            telefone = item.getOrFail("telefone"),
            cnpj = item.getOrFail("cnpj")
        )

        if (fabricaToEdit.idUser == 0) return false
        if (fabricaToEdit.cnpj.isEmpty() || fabricaToEdit.cnpj.isBlank()) return false

        return dbFabrica.update(fabricaToEdit)
    }

    override suspend fun addNewFabrica(item: Fabrica): Fabrica? {

        if (item.idUser == 0) return null
        if (item.cnpj.isEmpty() || item.cnpj.isBlank()) return null

        return dbFabrica.insert(item)
    }

    override suspend fun remove(id: Int): Boolean = dbFabrica.delete(id)
}