package com.example.capstone_project.controller

import com.example.capstone_project.model.Product
import com.example.capstone_project.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class ProductController(private val productService: ProductService) {
    @PostMapping("/products")
    fun addProduct(@RequestBody product: Product): ResponseEntity<String> {
        productService.addProduct(product)
        return ResponseEntity.ok("Product Added Successfully")
    }

    @GetMapping("/products")
    fun getProducts(): ResponseEntity<Set<Product>> {
        val products = productService.getAllProducts()
        return ResponseEntity.ok(products)
    }

    @DeleteMapping("/products/{productId}")
    fun deleteProduct(@PathVariable productId: Long): ResponseEntity<String> {
        val isDeleted = productService.deleteProduct(productId)
        return if (isDeleted) {
            ResponseEntity.ok("Product Deleted Successfully")
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product is Not Found")
        }
    }

    @GetMapping("/products?category={category}")
    fun getProductsByCategory(@PathVariable category: String): ResponseEntity<Set<Product>?> {
        val products = productService.getProductsByCategory(category)
        return ResponseEntity.ok(products)
    }

    @GetMapping("/products/{productId}")
    fun getProductById(@PathVariable productId: Long): ResponseEntity<Product?> {
        val product = productService.getProductById(productId)
        return ResponseEntity.ok(product)
    }

    @GetMapping("/products?search={productName}")
    fun getProductByName(@PathVariable productName: String): ResponseEntity<Product?> {
        val product = productService.getProductByName(productName)
        return ResponseEntity.ok(product)
    }
}