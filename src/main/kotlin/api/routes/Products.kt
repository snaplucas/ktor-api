package api.routes

import api.Products
import api.domain.model.Product
import org.jetbrains.ktor.locations.get
import org.jetbrains.ktor.locations.post
import org.jetbrains.ktor.response.header
import org.jetbrains.ktor.response.respond
import org.jetbrains.ktor.routing.Route

fun Route.products() {
    get<Products> {
        call.response.header("Access-Control-Allow-Origin", "*")
        val products = listOf(Product(1, "mesa", 50), Product(2, "cadeira", 20), Product(3, "cama", 500))
        call.respond(products)
    }

    post<Products> {

    }

}