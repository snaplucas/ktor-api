package api.data

import api.application.IProductRepository
import api.domain.model.Product
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class ProductRepository : IProductRepository {

    override fun findAll() = transaction { Products.selectAll().map { x -> x.toProduct() } }

    override fun findById(id: Int): Product = transaction { findProduct(byId(id)) }

    private fun findProduct(where: Op<Boolean>) = transaction {
        Products.select(where)
                .checkNull()
                .let(ResultRow::toProduct)
    }

    private fun byId(id: Int): Op<Boolean> = transaction { Products.id eq id }

    override fun insert(product: Product) = transaction {
        product.id = Products.insert({
            it[name] = product.name
            it[price] = product.price
        }) get Products.id
    }

    private fun Query.checkNull(): ResultRow = firstOrNull() ?: throw Exception("Product not found")

}