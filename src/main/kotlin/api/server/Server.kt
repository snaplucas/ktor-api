package api.server

import api.application.ProductService
import api.data.ProductRepository
import api.data.SetupDataBase
import api.exceptions.CustomException
import api.routes.index
import api.routes.products
import org.jetbrains.ktor.application.install
import org.jetbrains.ktor.features.CORS
import org.jetbrains.ktor.features.CallLogging
import org.jetbrains.ktor.features.DefaultHeaders
import org.jetbrains.ktor.features.StatusPages
import org.jetbrains.ktor.gson.GsonSupport
import org.jetbrains.ktor.host.embeddedServer
import org.jetbrains.ktor.http.HttpStatusCode
import org.jetbrains.ktor.jetty.Jetty
import org.jetbrains.ktor.locations.Locations
import org.jetbrains.ktor.response.respond
import org.jetbrains.ktor.routing.routing

fun startServer() = embeddedServer(Jetty, 8080) {
    SetupDataBase()
    install(DefaultHeaders)
    install(CORS)
    install(CallLogging)
    install(Locations)
    install(GsonSupport) {
        setPrettyPrinting()
    }
    install(StatusPages) {
        exception<Throwable> {
            val status = when (it) {
                is CustomException -> it.status
                else -> HttpStatusCode.InternalServerError
            }
            call.response.status(status)
            call.respond(it)
        }

        routing {
            index()
            products(ProductService(ProductRepository()))
        }
    }


}.start(wait = true)