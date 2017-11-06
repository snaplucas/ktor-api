package api.data

import api.domain.model.Product
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toProduct() = Product(
        id = this[Products.id],
        name = this[Products.name],
        price = this[Products.price]
)