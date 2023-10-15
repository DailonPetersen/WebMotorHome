package com.routes.controllers

import com.data.Anuncio
import com.database.dao.facades.DAOAnuncioInterface
import com.database.dao.facades.DAOPedidosInterface
import com.database.dao.implementations.DAOAnuncioInterfaceImpl
import com.routes.facades.AnuncioFacades
import io.ktor.http.*
import io.ktor.server.util.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AnuncioController: AnuncioFacades, KoinComponent {

    private val dbAnuncio  by inject<DAOAnuncioInterface>()
    override suspend fun getAll(): List<Anuncio> = dbAnuncio.getAll()

    override suspend fun anuncio(id: Int): Anuncio = dbAnuncio.get(id) ?: throw NoSuchElementException()

    override suspend fun editAnuncio(item: Parameters, id: Int): Boolean {
        val anuncioToEdit = Anuncio(
            idMotorhome = item.getOrFail("idMotorHome").toInt(),
            idCriador = item.getOrFail("idCriador").toInt(),
            precoAluguel = item.getOrFail("precoAluguel").toDouble(),
            precoVenda = item.getOrFail("precoVenda").toDouble(),
            descricao = item.getOrFail("descricao"),
            disponivelParaAluguel = item.getOrFail("disponivelParaAluguel").toBoolean(),
            dataDeDisponibilidadeExposicao = item.getOrFail("dataDeDisponibilidadeExposicao").toInt(),
            dataDeDisponibilidadeAluguel = item.getOrFail("dataDeDisponibilidadeAluguel").toIntOrNull()
        )

        if (anuncioToEdit.idMotorhome == 0) return false
        if (anuncioToEdit.idCriador == 0) return false
        //if (anuncioToAdd.precoAluguel.equals(0.00) || anuncioToAdd.precoCompra.equals(0.00)) return false

        return dbAnuncio.update(anuncioToEdit)
    }

    override suspend fun addNewAnuncio(item: Anuncio): Anuncio? {
        if (item.idMotorhome == 0) return null
        if (item.idCriador == 0) return null
        //if (anuncioToAdd.precoAluguel.equals(0.00) || anuncioToAdd.precoCompra.equals(0.00)) return null

        return dbAnuncio.insert(item)
    }

    override suspend fun remove(id: Int): Boolean = dbAnuncio.delete(id)
}