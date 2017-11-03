package api.routes

import api.ProductsRoute
import api.data.ProductRepository
import api.data.Products
import api.domain.model.Product
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.ktor.locations.get
import org.jetbrains.ktor.locations.post
import org.jetbrains.ktor.request.receive
import org.jetbrains.ktor.response.header
import org.jetbrains.ktor.response.respond
import org.jetbrains.ktor.routing.Route

fun Route.products(productRepository: ProductRepository) {
    get<ProductsRoute> {
        call.response.header("Access-Control-Allow-Origin", "*")
        val products = getProductList(productRepository)
        call.respond(products)
    }

    post<ProductsRoute> {
        val product = saveProduct(productRepository, call.receive())
        call.respond(product)
    }
}

suspend fun saveProduct(productRepository: ProductRepository, product: Product): Product {
    return transaction {
        create(Products)
        productRepository.insert(product)
    }
}

suspend fun getProductList(productRepository: ProductRepository) {
    return transaction {
        create(Products)
        productRepository.insert(Product(1, "carro", 5000))
        productRepository.selectAll()
    }
}

