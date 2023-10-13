package com.example.capstone_project.controller

import com.example.capstone_project.model.Order
import com.example.capstone_project.service.OrderService
import com.example.capstone_project.service.UserService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/users")
class OrderController(private val orderService: OrderService, private val userService: UserService) {
    @PostMapping("/{userId}/orders")
    fun orderByUser(@PathVariable userId: Long, @RequestBody order: Order): ResponseEntity<String> {
        if (!userService.isUserExists(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found")
        }
        orderService.addOrder(userId, order)
        return ResponseEntity.status(HttpStatus.OK).body("User Placed Successfully")
    }

    @GetMapping("/{userId}/orders")
    fun getOrdersByUserId(@PathVariable userId: Long): List<Order>? {
        return orderService.getOrdersByUserId(userId)
    }

    @DeleteMapping("/{userId}/orders/{orderId}")
    fun deleteOrder(@PathVariable userId: Long, @PathVariable orderId: Long): ResponseEntity<String> {
        val isOrderDeleted = orderService.deleteOrder(userId, orderId)
        return if (isOrderDeleted) {
            ResponseEntity.status(HttpStatus.OK).body("Order Deleted Successfully")
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Not Found")
        }
    }

    @DeleteMapping("/{userId}/orders/{orderId}/{productName}")
    fun deleteProduct(
        @PathVariable userId: Long,
        @PathVariable orderId: Long,
        @PathVariable productName: String
    ): ResponseEntity<String> {
        val isProductDeleted = orderService.deleteProduct(userId, orderId, productName)
        if (isProductDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body("$productName Deleted Successfully")
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("$productName is NOT Found")
    }

    @GetMapping("/{userId}/orders/date")
    fun getOrdersBetweenDates(
        @PathVariable userId: Long,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) startdate: LocalDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) enddate: LocalDate
    ): ResponseEntity<List<Order>> {
        val orders = orderService.getOrdersByUserId(userId)
        val getOrders = orderService.getOrdersBetweenDates(orders, startdate, enddate)
        return ResponseEntity.ok(getOrders)
    }

    @GetMapping("/orders")
    fun getAllOrders(): ResponseEntity<List<Order>> {
        val orders = orderService.getAllOrders()
        return ResponseEntity.ok(orders)
    }
}