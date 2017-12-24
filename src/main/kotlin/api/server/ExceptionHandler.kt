package api.server

import api.exceptions.CustomException
import org.jetbrains.ktor.features.StatusPages
import org.jetbrains.ktor.http.HttpStatusCode
import org.jetbrains.ktor.response.respond

fun StatusPages.Configuration.handleExceptions() {
    exception<Throwable> {
        val status = when (it) {
            is CustomException -> it.status
            else -> HttpStatusCode.InternalServerError
        }
        call.response.status(status)
        call.respond(it)
    }
}