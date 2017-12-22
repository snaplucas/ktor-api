package api.server

import api.FactoryConfiguration
import api.routes.index
import api.routes.products
import org.jetbrains.ktor.routing.Routing
import org.jetbrains.ktor.routing.route

fun Routing.setup() = route("api") {
    index()
    products(FactoryConfiguration.productService)
}