package api

import api.application.IProductRepository
import api.application.IProductService
import api.application.ProductService
import api.data.ProductRepository
import api.server.startServer
import org.koin.dsl.module.applicationContext
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.inject

fun main(args: Array<String>) {
    val myModule = applicationContext {
        provide { ProductRepository() as IProductRepository }
        provide { ProductService(get()) as IProductService }
    }
    startKoin(listOf(myModule))

    startServer()
}

object FactoryConfiguration : KoinComponent {
    val productService: IProductService by inject()
}