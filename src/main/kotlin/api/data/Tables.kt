package api.data

import org.jetbrains.exposed.sql.Table

object Products : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val name = varchar("name", length = 20)
    val price = integer("price")
}
