package api

import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.application.install
import org.jetbrains.ktor.features.CallLogging
import org.jetbrains.ktor.features.DefaultHeaders
import org.jetbrains.ktor.gson.GsonSupport
import org.jetbrains.ktor.host.embeddedServer
import org.jetbrains.ktor.jetty.Jetty
import org.jetbrains.ktor.locations.Locations
import org.jetbrains.ktor.response.header
import org.jetbrains.ktor.response.respond
import org.jetbrains.ktor.routing.Routing
import org.jetbrains.ktor.routing.get

fun main(args: Array<String>) {
    embeddedServer(Jetty, 8080, module = Application::main).start()
}

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Locations)
    install(GsonSupport) {
        setPrettyPrinting()
    }
    install(Routing) {
        get("/") {
            call.response.header("Access-Control-Allow-Origin", "*")
            call.respond(Foo())
        }
        get("/products") {
            call.response.header("Access-Control-Allow-Origin", "*")
            val products = listOf(Product(1, "mesa", 50), Product(2, "cadeira", 20), Product(3, "cama", 500))
            call.respond(products)
        }
    }
}

data class Foo(val name: String = "Joao", val age: Int = 30)

data class Product(val id: Int, val name: String, val price: Int)