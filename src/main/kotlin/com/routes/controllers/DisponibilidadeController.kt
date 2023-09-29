package com.routes.controllers

import com.data.Disponibilidade
import com.database.dao.implementations.DAODisponibilidadeInterfaceImpl
import io.ktor.http.*
import io.ktor.server.util.*
import org.koin.core.component.KoinComponent

class DisponibilidadeController: KoinComponent {
    
    private val dbDisponibilidade = DAODisponibilidadeInterfaceImpl()

    suspend fun getAll(): List<Disponibilidade> = dbDisponibilidade.getAll()

    suspend fun disponibilidade(id: Int): Disponibilidade = dbDisponibilidade.get(id) ?: throw NoSuchElementException()

    suspend fun editDisponibilidade(item: Disponibilidade): Boolean {

        if (item.dataInicio > item.dataFim ) return false

        return dbDisponibilidade.update(item)
    }

    suspend fun addNewDisponibilidade(item: Disponibilidade): Disponibilidade? {

        if (item.dataInicio > item.dataFim ) return null

        return dbDisponibilidade.insert(item)
    }

    suspend fun remove(id: Int): Boolean = dbDisponibilidade.delete(id)
}