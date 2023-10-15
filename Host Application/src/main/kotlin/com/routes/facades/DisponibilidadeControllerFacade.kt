package com.routes.facades

import com.data.Disponibilidade

interface DisponibilidadeControllerFacade {

    suspend fun getAll(): List<Disponibilidade>
    suspend fun disponibilidade(id: Int): Disponibilidade
    suspend fun editDisponibilidade(item: Disponibilidade): Boolean
    suspend fun addNewDisponibilidade(item: Disponibilidade): Disponibilidade?
    suspend fun remove(id: Int): Boolean
}