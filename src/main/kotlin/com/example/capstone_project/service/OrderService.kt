package com.example.capstone_project.service

import com.example.capstone_project.model.Order
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class OrderService(private val userService: UserService) {

    val users = userService.users
    fun addOrder(userId: Long, order: Order) {
        val user = users.find {
            it.userId == userId

        }
        user?.orders?.add(order)
    }

    fun getOrdersByUserId(userId: Long): List<Order>? {
        return users.find { it.userId == userId }?.orders?.toList()
    }
    fun deleteOrder(userId: Long, orderId: Long) : Boolean {

        val user = userService.getUserById(userId) ?: return false
        println(user)

//        val removed = user.orders.removeIf { it.orderId == orderId }

//        val user = users.find { it.userId == userId }
//        user?.orders?.removeIf { it.orderId == orderId }

        return true
    }

    fun isOrderExists(orderId: Long?): Boolean {
        return orderId?.let { userId ->
            users.any { user ->
                user.orders?.any {
                    it.orderId == userId
                } ?: false
            }
        } ?: false
    }

    fun deleteProduct(userId: Long, orderId: Long, productName: String): Boolean {
        val isUserExists = userService.isUserExists(userId)
        val isOrderExists = isOrderExists(orderId)
        if (!(isOrderExists && isUserExists)) {
            return false
        }
        val user = users.find {
            it.userId == userId
        }
        val order = user?.orders?.find { it.orderId == orderId }
        order?.products?.removeIf { it.product.name == productName }
        return true
    }

    fun getOrdersBetweenDates(order: List<Order>?, startdate: LocalDate, enddate: LocalDate): List<Order>? {
        return order?.filter { it.orderDate in startdate..enddate }
    }

    fun getAllOrders(): List<Order> {
        return users.flatMap { it.orders ?: emptyList() }
    }
}