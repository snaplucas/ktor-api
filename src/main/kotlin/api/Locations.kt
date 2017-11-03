package api

import org.jetbrains.ktor.locations.location

@location("/products")
data class ProductsRoute(val id: Int = 1, val name: String = "", val price: Int = 0)