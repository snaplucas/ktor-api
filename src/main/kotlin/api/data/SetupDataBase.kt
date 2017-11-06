package api.data

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class SetupDataBase {
    init {
        Database.connect("jdbc:h2:~/test", driver = "org.h2.Driver")
        transaction {
            SchemaUtils.create(Products)
        }
    }
}