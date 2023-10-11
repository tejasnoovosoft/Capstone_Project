package com.example.capstone_project.service

import com.example.capstone_project.model.Order
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
        return users.any { it.id == userId }
    }

    fun getAllUsers(): Optional<Set<User>> {
        return Optional.ofNullable(users)
    }

    fun getUserById(id: Long): Optional<User> {
        return Optional.ofNullable(users.find { it.id == id })
    }

    fun updateUser(user: User) {
        val oldUserData = users.find { user.id == it.id }
        users.remove(oldUserData)
        users.add(user)
    }

    fun deleteUser(id: Long) {
        val user = users.find { it.id == id }
        users.remove(user)
    }
}