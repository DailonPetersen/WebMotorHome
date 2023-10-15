package com.routes.controllers

import com.data.Disponibilidade
import com.database.dao.facades.DAODisponibilidadeInterface
import com.database.dao.facades.DAOFabricaInterface
import com.database.dao.implementations.DAODisponibilidadeInterfaceImpl
import com.routes.facades.DisponibilidadeControllerFacade
import io.ktor.http.*
import io.ktor.server.util.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DisponibilidadeController:DisponibilidadeControllerFacade,  KoinComponent {
    
    private val dbDisponibilidade by inject<DAODisponibilidadeInterface>()

    override suspend fun getAll(): List<Disponibilidade> = dbDisponibilidade.getAll()

    override suspend fun disponibilidade(id: Int): Disponibilidade = dbDisponibilidade.get(id) ?: throw NoSuchElementException()

    override suspend fun editDisponibilidade(item: Disponibilidade): Boolean {

        if (item.dataInicio > item.dataFim ) return false

        return dbDisponibilidade.update(item)
    }

    override suspend fun addNewDisponibilidade(item: Disponibilidade): Disponibilidade? {

        if (item.dataInicio > item.dataFim ) return null

        return dbDisponibilidade.insert(item)
    }

    override suspend fun remove(id: Int): Boolean = dbDisponibilidade.delete(id)
}