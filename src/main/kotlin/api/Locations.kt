package api

import org.jetbrains.ktor.locations.location

@location("/products")
data class Products(val id: Int = 1, val name: String = "", val type: String = "")