package com.example.webmotorhomeapp.db

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.webmotorhomeapp.data.anuncio.Anuncio
import com.example.webmotorhomeapp.data.chat.Chat
import com.example.webmotorhomeapp.data.chat.Message
import com.example.webmotorhomeapp.data.disponibilidade.Disponibilidade
import com.example.webmotorhomeapp.data.factory.Fabrica
import com.example.webmotorhomeapp.data.login.AuthToken
import com.example.webmotorhomeapp.data.motorhome.Motorhome
import com.example.webmotorhomeapp.data.pedido.Pedido
import com.example.webmotorhomeapp.data.user.User
import com.example.webmotorhomeapp.db.dao.AnuncioDAO
import com.example.webmotorhomeapp.db.dao.ChatDAO
import com.example.webmotorhomeapp.db.dao.DisponibilidadeDAO
import com.example.webmotorhomeapp.db.dao.FabricaDAO
import com.example.webmotorhomeapp.db.dao.MessageDAO
import com.example.webmotorhomeapp.db.dao.MotorhomeDAO
import com.example.webmotorhomeapp.db.dao.PedidoDAO
import com.example.webmotorhomeapp.db.dao.TokenDAO
import com.example.webmotorhomeapp.db.dao.UserDAO

@Database(entities = [User::class,
    Motorhome::class,
    Fabrica::class,
    Disponibilidade::class,
    Anuncio::class,
    Pedido::class,
    Chat::class,
    Message::class,
    AuthToken::class], version = 3)
abstract class AppDatabase: RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstanceDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, "webmotorhome-app"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }


    abstract fun userDAO(): UserDAO
    abstract fun motorhomeDAO(): MotorhomeDAO
    abstract fun fabricaDAO(): FabricaDAO
    abstract fun disponibilidadeDAO(): DisponibilidadeDAO
    abstract fun anuncioDAO(): AnuncioDAO
    abstract fun pedidoDAO(): PedidoDAO
    abstract fun chatDAO(): ChatDAO
    abstract fun messageDAO(): MessageDAO
    abstract fun tokenDAO(): TokenDAO


}