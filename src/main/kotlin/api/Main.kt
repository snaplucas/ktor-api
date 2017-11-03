package api

import api.data.ProductRepository
import api.data.Products
import api.routes.index
import api.routes.products
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.application.install
import org.jetbrains.ktor.features.CallLogging
import org.jetbrains.ktor.features.DefaultHeaders
import org.jetbrains.ktor.gson.GsonSupport
import org.jetbrains.ktor.host.embeddedServer
import org.jetbrains.ktor.jetty.Jetty
import org.jetbrains.ktor.locations.Locations
import org.jetbrains.ktor.routing.routing

fun main(args: Array<String>) {
    initDataBase()
    embeddedServer(Jetty, 8080, module = Application::main).start()
}

private fun initDataBase() {
    Database.connect("jdbc:h2:~/test", driver = "org.h2.Driver")
    transaction {
        SchemaUtils.create(Products)
    }
}

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Locations)
    install(GsonSupport) {
        setPrettyPrinting()
    }
    routing {
        index()
        products(ProductRepository())
    }
}