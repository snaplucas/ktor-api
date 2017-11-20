package api.server

import api.data.SetupDataBase
import api.exceptions.CustomException
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
import org.jetbrains.ktor.routing.Routing

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
    }
    install(Routing) {
        setup()
    }

}.start(wait = true)