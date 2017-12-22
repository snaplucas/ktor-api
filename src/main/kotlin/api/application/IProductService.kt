package api.application

import api.domain.model.Product

interface IProductService {

    suspend fun findAll(): List<Product>
    suspend fun findById(id: Int): Product
    suspend fun saveProduct(product: Product): Product
}