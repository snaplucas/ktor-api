package api.data

import api.domain.model.Product
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class ProductRepository {

    fun selectAll(): List<Product> = Products.selectAll().map { Product(it[Products.id], it[Products.name], it[Products.price]) }

    fun insert(product: Product): Product {
        product.id = Products.insert({
            it[name] = product.name
            it[price] = product.price
        }) get Products.id
        return product
    }
}