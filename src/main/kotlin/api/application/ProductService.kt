package api.application

import api.domain.model.Product

class ProductService(private val productRepository: IProductRepository) : IProductService {

    override suspend fun findAll(): List<Product> = productRepository.findAll()

    override suspend fun findById(id: Int) = productRepository.findById(id)

    override suspend fun saveProduct(product: Product): Product = productRepository.insert(product)
}