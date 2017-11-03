package api.routes

import api.ProductsRoute
import api.data.ProductRepository
import api.domain.model.Product
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.ktor.locations.get
import org.jetbrains.ktor.locations.post
import org.jetbrains.ktor.request.receive
import org.jetbrains.ktor.response.respond
import org.jetbrains.ktor.routing.Route

fun Route.products(productRepository: ProductRepository) {
    get<ProductsRoute> {
        val products = getAllProducts(productRepository)
        call.respond(products)
    }

    post<ProductsRoute> {
        val product = saveProduct(productRepository, call.receive())
        call.respond(product)
    }
}

suspend fun saveProduct(productRepository: ProductRepository, product: Product): Product {
    return transaction {
        productRepository.insert(product)
    }
}

suspend fun getAllProducts(productRepository: ProductRepository): List<Product> {
    return transaction {
        productRepository.selectAll()
    }
}