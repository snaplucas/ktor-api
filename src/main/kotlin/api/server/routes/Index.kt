package api.server.routes

import org.jetbrains.ktor.response.respondText
import org.jetbrains.ktor.routing.Route
import org.jetbrains.ktor.routing.get

fun Route.index() {
    get("/") {
        call.respondText { "Hello, World!" }
    }

}