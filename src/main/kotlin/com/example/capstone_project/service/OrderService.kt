package com.example.capstone_project.service

import com.example.capstone_project.model.Order
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderService(userService: UserService) {

    val users = userService.users
    fun addOrder(id: Long, order: Order) {
        val user = users.find { it.id == id }
        user?.orders?.add(order)
    }

    fun getOrdersByUserId(id: Long): Optional<List<Order>> {
        return Optional.ofNullable(users.find { it.id == id }?.orders)
    }

    fun deleteOrder(userId: Long, orderId: Long) {
        val user = users.find { it.id == userId }
        user?.orders?.removeIf { it.orderId == orderId }
    }
}