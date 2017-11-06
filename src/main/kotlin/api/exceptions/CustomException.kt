package api.exceptions

import org.jetbrains.ktor.http.HttpStatusCode

class CustomException : Exception() {

    val status = HttpStatusCode.Forbidden
}