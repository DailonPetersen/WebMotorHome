package com.routes.facades

import com.data.MotorHome
import io.ktor.http.*

interface MotorhomeControllerFacade {

    suspend fun getAll(): List<MotorHome>
    suspend fun motorhome(id: Int): MotorHome
    suspend fun editMotorhome(item: Parameters, id: Int): Boolean
    suspend fun addNewMotorHome(item: Parameters): MotorHome?
    suspend fun remove(id: Int): Boolean
}