package api.routes

import org.jetbrains.ktor.application.ApplicationCall
import org.jetbrains.ktor.response.header
import org.jetbrains.ktor.response.respondText
import org.jetbrains.ktor.routing.Route
import org.jetbrains.ktor.routing.get

fun Route.index(){
    get("/"){
        call.response.header("Access-Control-Allow-Origin", "*")
        call.respondText { "Hello, World!" }
    }

}