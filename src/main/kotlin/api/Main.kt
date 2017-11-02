package api

import api.routes.index
import api.routes.products
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
    embeddedServer(Jetty, 8080, module = Application::main).start()
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
        products()
    }
}