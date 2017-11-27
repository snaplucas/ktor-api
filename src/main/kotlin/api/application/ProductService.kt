package api.application

import api.data.ProductRepository
import api.domain.model.Product

class ProductService(private val productRepository: ProductRepository) {

    suspend fun findAll(): List<Product> = productRepository.findAll()

    suspend fun findById(id: Int) = productRepository.findById(id)

    suspend fun saveProduct(product: Product): Product = productRepository.insert(product)
}