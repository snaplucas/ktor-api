package api.server.routes

import api.application.IProductService
import api.application.exceptions.CustomException
import api.server.ProductsRoute
import org.eclipse.jetty.http.HttpStatus
import org.jetbrains.ktor.locations.post
import org.jetbrains.ktor.request.receive
import org.jetbrains.ktor.response.respond
import org.jetbrains.ktor.routing.Route
import org.jetbrains.ktor.routing.get

fun Route.products(productService: IProductService) {

    get("/products") {
        call.respond(productService.findAll())
    }

    get("/products/{id}") {
        val id = try {
            call.parameters["id"]?.toInt()
        } catch (e: Exception) {
            0
        }
        val product = id?.let { x -> productService.findById(x) }
        call.respond(product ?: HttpStatus.NOT_FOUND_404)
    }

    get("/exception") {
        throw CustomException()
    }

    post<ProductsRoute> {
        productService.saveProduct(call.receive())
        call.respond(HttpStatus.OK_200)
    }
}