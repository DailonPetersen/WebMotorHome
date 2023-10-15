package com.di

import com.database.dao.facades.*
import com.database.dao.implementations.*
import com.routes.controllers.*
import com.routes.facades.*
import org.koin.core.context.startKoin
import org.koin.dsl.module

object ModulesKoinInjection {

    val koinModulesController = module(createdAtStart = true) {
        single<AnuncioFacades> { AnuncioController() }
        single<MotorhomeControllerFacade> { MotorhomeController() }
        single<UserControllerFacade> { UserController() }
        single<PedidoControllerFacade> { PedidoController() }
        single<FabricaControllerFacade> { FabricaController() }
        single<DisponibilidadeControllerFacade> { DisponibilidadeController() }
        single<ChatControllerFacade> { ChatController() }
        single<MessageControllerFacade> { MessageController() }
        single<LoginControllerFacade> { LoginController() }
    }

    val koinModulesFacadesImpl = module(createdAtStart = true)  {
        single<DAOMotorHomeFacade> { DAOMotorhomeInterfaceImpl() }
        single<DAOUsuarioFacade> { DAOUserFacadeImpl() }
        single<DAOAnuncioInterface> { DAOAnuncioInterfaceImpl() }
        single<DAODisponibilidadeInterface> { DAODisponibilidadeInterfaceImpl() }
        single<DAOFabricaInterface> { DAOFabricaInterfaceImpl() }
        single<DAOPedidosInterface> { DAOPedidosInterfaceImpl() }
        single<DAOChatInterface> { DAOChatInterfaceImpl() }
        single<DAOMessageInterface> { DAOMessageInterfaceImpl() }
    }

//    fun init() {
//        startKoin {
//            koinModulesController
//            koinModulesFacadesImpl
//        }
//    }
}