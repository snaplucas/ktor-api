package api.application

import api.domain.model.Product

interface IProductRepository {

    fun findAll(): List<Product>
    fun findById(id: Int): Product
    fun insert(product: Product)
}