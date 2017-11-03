package api.routes

import api.ProductsRoute
import api.application.ProductService
import org.jetbrains.ktor.locations.get
import org.jetbrains.ktor.locations.post
import org.jetbrains.ktor.request.receive
import org.jetbrains.ktor.response.respond
import org.jetbrains.ktor.routing.Route

fun Route.products(productService: ProductService) {

    get<ProductsRoute> {
        val products = productService.getAllProducts()
        call.respond(products)
    }

    post<ProductsRoute> {
        val product = productService.saveProduct(call.receive())
        call.respond(product)
    }
}