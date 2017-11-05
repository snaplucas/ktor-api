package api.data

import api.domain.model.Product
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class ProductRepository {

    fun findAll() = Products.selectAll().map { x -> mapToProduct(x) }

    fun findById(id: Int): Product? = Products.select { Products.id.eq(id) }
            .map { x -> mapToProduct(x) }
            .firstOrNull()

    private fun mapToProduct(x: ResultRow) = Product(x[Products.id], x[Products.name], x[Products.price])

    fun insert(product: Product): Product {
        product.id = Products.insert({
            it[name] = product.name
            it[price] = product.price
        }) get Products.id
        return product
    }
}