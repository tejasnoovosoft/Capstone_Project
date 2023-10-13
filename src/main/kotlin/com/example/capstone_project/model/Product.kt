package com.example.capstone_project.model

data class Product(
    val productId: Long,
    val name: String?,
    val price: Double?,
    val quantity: Long?,
    val category: String?
) {
    fun copy(quantity: Long? = this.quantity) = Product(productId, name, price, quantity, category)
}

data class ProductOrder(val product: Product, val isDelivered: Boolean)
