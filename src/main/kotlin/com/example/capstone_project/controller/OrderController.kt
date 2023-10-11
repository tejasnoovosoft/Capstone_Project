package com.example.capstone_project.controller

import com.example.capstone_project.model.Order
import com.example.capstone_project.service.OrderService
import com.example.capstone_project.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class OrderController(private val orderService: OrderService, private val userService: UserService) {
    @PostMapping("/{id}/order")
    fun orderByUser(@PathVariable id: Long, @RequestBody order: Order): ResponseEntity<String> {
        if (!userService.isUserExists(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found")
        }
        orderService.addOrder(id, order)
        return ResponseEntity.status(HttpStatus.OK).body("User Placed Successfully")
    }

    @GetMapping("/{id}/ordered_products")
    fun getOrdersByUserId(@PathVariable id: Long): ResponseEntity<List<Order>> {
        val orders = orderService.getOrdersByUserId(id)
        return ResponseEntity.of(orders)
    }

    @DeleteMapping("/{id}/delete_order/{order_id}")
    fun deleteOrder(@PathVariable id: Long, @PathVariable order_id: Long): ResponseEntity<String> {
        if (!userService.isUserExists(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found")
        }
        orderService.deleteOrder(id, order_id)
        return ResponseEntity.status(HttpStatus.OK).body("Order Deleted Successfully")
    }
}