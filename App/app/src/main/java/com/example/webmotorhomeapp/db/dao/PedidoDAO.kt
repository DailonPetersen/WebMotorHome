package com.example.webmotorhomeapp.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.webmotorhomeapp.data.pedido.Pedido

@Dao
interface PedidoDAO {

    @Query("SELECT * FROM Pedido")
    fun getAll(): List<Pedido>

    @Query("SELECT * FROM Pedido WHERE id IN (:pedidoIds)")
    fun loadAllByIds(pedidoIds: IntArray): List<Pedido>

    @Query("SELECT * FROM Pedido WHERE idFabrica LIKE :idFabrica LIMIT 1")
    fun findPedidoByIdFabrica(idFabrica: Int): Pedido

    @Insert
    fun insertAll(pedidos: List<Pedido>)

    @Insert
    fun insertPedido(pedido: Pedido)

    @Delete
    fun delete(Pedido: Pedido)

}