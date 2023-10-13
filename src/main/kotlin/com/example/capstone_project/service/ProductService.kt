package com.example.capstone_project.service

import com.example.capstone_project.model.Product
import org.springframework.stereotype.Service

@Service
class ProductService {

    val products = mutableSetOf<Product>()

    fun addProduct(product: Product) {
        val existingProduct = products.find { it.productId == product.productId }
        if (existingProduct != null) {
            val newQuantity = (existingProduct.quantity ?: 0) + (product.quantity ?: 0)
            products.remove(existingProduct)
            products.add(existingProduct.copy(quantity = newQuantity))
        } else {
            products.add(product)
        }
    }

    fun getAllProducts(): Set<Product> {
        return products
    }

    fun deleteProduct(productId: Long): Boolean {
        return products.removeIf { it.productId == productId }
    }

    fun getProductsByCategory(category: String): Set<Product> {
        return products.filter { it.category == category }.toSet()
    }

    fun getProductById(productId: Long): Product? {
        return products.find { it.productId == productId }
    }

    fun getProductByName(productName: String): Product? {
        return products.find { it.name == productName }
    }
}