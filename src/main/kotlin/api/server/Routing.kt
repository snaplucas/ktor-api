package api.server

import api.application.ProductService
import api.data.ProductRepository
import api.routes.index
import api.routes.products
import org.jetbrains.ktor.routing.Routing
import org.jetbrains.ktor.routing.route

fun Routing.setup() = route("api") {
    val productRepository = ProductRepository()
    val productService = ProductService(productRepository)

    index()
    products(productService)
}