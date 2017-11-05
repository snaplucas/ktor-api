package api.routes

import api.ProductsRoute
import api.application.ProductService
import org.eclipse.jetty.http.HttpStatus
import org.jetbrains.ktor.locations.get
import org.jetbrains.ktor.locations.post
import org.jetbrains.ktor.request.receive
import org.jetbrains.ktor.response.respond
import org.jetbrains.ktor.routing.Route
import org.jetbrains.ktor.routing.get

fun Route.products(productService: ProductService) {

    get<ProductsRoute> {
        val products = productService.findAll()
        call.respond(products)
    }

    get("/products/{id}") {
        val id = call.parameters["id"]?.toInt()
        val product = id?.let { x -> productService.findById(x) }
        call.respond(product ?: HttpStatus.NOT_FOUND_404)
    }

    post<ProductsRoute> {
        productService.saveProduct(call.receive())
        call.respond(HttpStatus.OK_200)
    }
}