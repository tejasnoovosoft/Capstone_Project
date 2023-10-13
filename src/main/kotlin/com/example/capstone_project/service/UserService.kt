package com.example.capstone_project.service

import com.example.capstone_project.model.ProductOrder
import com.example.capstone_project.model.User
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService {

    val users = mutableSetOf<User>()
    fun addUser(user: User) {
        users.add(user)
    }

    fun isUserExists(userId: Long): Boolean {
        return users.any { it.userId == userId }
    }

    fun getAllUsers(): Optional<Set<User>> {
        return Optional.ofNullable(users)
    }

    fun getUserById(id: Long): User? {
        return users.find { it.userId == id }
    }

    fun updateUser(user: User) {
        users.removeIf { it.userId == user.userId }
        users.add(user)
    }
    fun deleteUser(id: Long): Boolean {
        return users.removeIf { it.userId == id }
    }

    fun getDeliveredOrders(userId: Long): List<ProductOrder>? {
        val user = getUserById(userId)
        return user?.orders
            ?.flatMap { order ->
                order.products?.filter { it.isDelivered == true } ?: emptyList()
            } ?: emptyList()
    }
}