package com.database.dao.motorhome

import com.data.MotorHome
import javax.swing.text.StyledEditorKit.BoldAction

interface DAOMotorHomeFacade {
    suspend fun getAll(): List<MotorHome>
    suspend fun get(id: Int): MotorHome?
    suspend fun insert(motorHome: MotorHome): MotorHome?
    suspend fun update(motorHome: MotorHome): Boolean
    suspend fun delete(id: Int): Boolean
}