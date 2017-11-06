package api.data

import api.domain.model.Product
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class ProductRepository {

    fun findAll() = Products.selectAll().map { x -> x.toProduct() }

    fun findById(id: Int): Product = findProduct(byId(id))

    private fun findProduct(where: Op<Boolean>) = transaction {
        Products.select(where)
                .checkNull()
                .let(ResultRow::toProduct)
    }

    private fun byId(id: Int): Op<Boolean> = Products.id eq id

    fun insert(product: Product): Product {
        product.id = Products.insert({
            it[name] = product.name
            it[price] = product.price
        }) get Products.id
        return product
    }

    private fun Query.checkNull(): ResultRow = firstOrNull() ?: throw Exception("Product not found")

}