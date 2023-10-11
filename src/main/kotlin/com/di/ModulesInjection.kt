package com.di

import com.database.dao.implementations.*
import com.routes.controllers.*
import org.koin.core.context.startKoin
import org.koin.dsl.module

object ModulesKoinInjection {

    private val koinModulesController = module {
        single<MotorhomeController> { MotorhomeController() }
        single<UserController> { UserController() }
        single<PedidoController> { PedidoController() }
        single<FabricaController> { FabricaController() }
        single<DisponibilidadeController> { DisponibilidadeController() }
        single<AnuncioController> { AnuncioController() }
        single<ChatController> { ChatController() }
        single<MessageController> { MessageController() }
        single<LoginController> { LoginController() }
    }

    private val koinModulesFacadesImpl = module {
        single<DAOMotorhomeInterfaceImpl> { DAOMotorhomeInterfaceImpl() }
        single<DAOUserFacadeImpl> { DAOUserFacadeImpl() }
        single<DAOAnuncioInterfaceImpl> { DAOAnuncioInterfaceImpl() }
        single<DAODisponibilidadeInterfaceImpl> { DAODisponibilidadeInterfaceImpl() }
        single<DAOFabricaInterfaceImpl> { DAOFabricaInterfaceImpl() }
        single<DAOPedidosInterfaceImpl> { DAOPedidosInterfaceImpl() }
        single<DAOChatInterfaceImpl> { DAOChatInterfaceImpl() }
        single<DAOMessageInterfaceImpl> { DAOMessageInterfaceImpl() }
    }

    fun init() {
        startKoin {
            koinModulesController
            koinModulesFacadesImpl
        }
    }
}