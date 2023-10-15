package com.routes.controllers

import com.data.MotorHome
import com.database.dao.facades.DAOMotorHomeFacade
import com.database.dao.implementations.DAOMotorhomeInterfaceImpl
import com.routes.facades.MotorhomeControllerFacade
import io.ktor.http.*
import io.ktor.server.util.*
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MotorhomeController: MotorhomeControllerFacade, KoinComponent {

    private val dbMotorHome by inject<DAOMotorHomeFacade>()

    override suspend fun getAll(): List<MotorHome> = dbMotorHome.getAll()

    override suspend fun motorhome(id: Int): MotorHome = dbMotorHome.get(id) ?: throw NoSuchElementException()

    override suspend fun editMotorhome(item: Parameters, id: Int): Boolean {
        val motorHomeToEdit = MotorHome(
            modelo = item.getOrFail("modelo"),
            ano = item.getOrFail("ano").toInt(),
            descricao = item.getOrFail("descricao"),
            avaliacao = item.getOrFail("avaliacao").toInt(),
            exposicao = item.getOrFail("exposicao").toBoolean(),
            montadora = item.getOrFail("montadora"),
            placa = item.getOrFail("placa")
        )

        if (motorHomeToEdit.id != id) return false
        if (motorHomeToEdit.descricao.length > 250) return false
        if (motorHomeToEdit.avaliacao !in 0..5) return false

        return dbMotorHome.update(motorHomeToEdit)
    }

    override suspend fun addNewMotorHome(item: Parameters): MotorHome? {
        val motorHomeToAdd = MotorHome(
            modelo = item.getOrFail("modelo"),
            ano = item.getOrFail("ano").toInt(),
            descricao = item.getOrFail("descricao"),
            avaliacao = item.getOrFail("avaliacao").toInt(),
            exposicao = item.getOrFail("exposicao").toBoolean(),
            montadora = item.getOrFail("montadora"),
            placa = item.getOrFail("placa")
        )

        if (motorHomeToAdd.descricao.length > 250) return null
        if (motorHomeToAdd.avaliacao !in 0..5) return null

        return dbMotorHome.insert(motorHomeToAdd)
    }

    override suspend fun remove(id: Int): Boolean = dbMotorHome.delete(id)

}