package com.di

import com.database.dao.implementations.*
import com.routes.controllers.MotorhomeController
import org.koin.core.context.startKoin
import org.koin.dsl.module

object ModulesKoinInjection {

    private val koinModulesController = module {
        single<MotorhomeController> { MotorhomeController() }
    }

    private val koinModulesFacadesImpl = module {
        single<DAOMotorhomeInterfaceImpl> { DAOMotorhomeInterfaceImpl() }
        single<DAOUserFacadeImpl> { DAOUserFacadeImpl() }
        single<DAOAnuncioInterfaceImpl> { DAOAnuncioInterfaceImpl() }
        single<DAODisponibilidadeInterfaceImpl> { DAODisponibilidadeInterfaceImpl() }
        single<DAOFabricaInterfaceImpl> { DAOFabricaInterfaceImpl() }
        single<DAOPedidosInterfaceImpl> { DAOPedidosInterfaceImpl() }
    }

    fun init() {
        startKoin {
            koinModulesController
            koinModulesFacadesImpl
        }
    }
}