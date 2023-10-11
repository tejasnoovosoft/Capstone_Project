package com.example.capstone_project.controller

import com.example.capstone_project.model.Order
import com.example.capstone_project.service.OrderService
import com.example.capstone_project.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class OrderController(private val orderService: OrderService, private val userService: UserService) {
    @PostMapping("/{id}/orders")
    fun orderByUser(@PathVariable id: Long, @RequestBody order: Order): ResponseEntity<String> {
        if (!userService.isUserExists(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found")
        }
        orderService.addOrder(id, order)
        return ResponseEntity.status(HttpStatus.OK).body("User Placed Successfully")
    }

    @GetMapping("/{id}/orders")
    fun getOrdersByUserId(@PathVariable id: Long): ResponseEntity<List<Order>> {
        val orders = orderService.getOrdersByUserId(id)
        return ResponseEntity.of(orders)
    }

    @DeleteMapping("/{id}/{orderId}")
    fun deleteOrder(@PathVariable id: Long, @PathVariable orderId: Long): ResponseEntity<String> {
        if (!userService.isUserExists(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found")
        }
        orderService.deleteOrder(id, orderId)
        return ResponseEntity.status(HttpStatus.OK).body("Order Deleted Successfully")
    }
}