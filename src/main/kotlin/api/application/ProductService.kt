package api.application

import api.data.ProductRepository
import api.domain.model.Product
import org.jetbrains.exposed.sql.transactions.transaction

class ProductService(private val productRepository: ProductRepository) {

    suspend fun findAll(): List<Product> = transaction { productRepository.findAll() }

    suspend fun findById(id: Int) = transaction { productRepository.findById(id) }

    suspend fun saveProduct(product: Product): Product = transaction { productRepository.insert(product) }
}