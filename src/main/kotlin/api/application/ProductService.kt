package api.application

import api.data.ProductRepository
import api.domain.model.Product
import org.jetbrains.exposed.sql.transactions.transaction

class ProductService(private val productRepository: ProductRepository) {

    suspend fun saveProduct(product: Product): Product {
        return transaction {
            productRepository.insert(product)
        }
    }

    suspend fun getAllProducts(): List<Product> {
        return transaction {
            productRepository.selectAll()
        }
    }
}