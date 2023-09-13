package com.di

import com.routes.motorhome.MotorhomeController
import org.koin.core.context.startKoin
import org.koin.dsl.module

object ModulesKoinInjection {

    private val koinModulesController = module {
        single<MotorhomeController> { MotorhomeController() }
    }

    fun init() {
        startKoin {
            koinModulesController
        }
    }
}