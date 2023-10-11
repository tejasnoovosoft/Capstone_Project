package com.example.capstone_project.controller

import com.example.capstone_project.model.User
import com.example.capstone_project.service.EmailService
import com.example.capstone_project.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class UserController(private val userService: UserService, private val emailService: EmailService) {
    @PostMapping("/users")
    fun createNewUser(@RequestBody user: User): ResponseEntity<String> {
        return when {
            user.id == null || user.email == null -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid User")
            userService.isUserExists(user.id) -> ResponseEntity.status(HttpStatus.CONFLICT).body("User Already Exists")
            else -> {
                userService.addUser(user)
                emailService.sendRegistrationEmail(user.email)
                ResponseEntity.status(HttpStatus.CREATED).body("User Successfully Created")
            }
        }
    }

    @GetMapping("/users")
    fun getAllUsers(): ResponseEntity<Set<User>> {
        val users = userService.getAllUsers()
        return ResponseEntity.of(users)
    }

    @GetMapping("/users/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<User> {
        val user = userService.getUserById(id)
        return ResponseEntity.of(user)
    }

    @PutMapping("/users/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody user: User): ResponseEntity<String> {
        if (!userService.isUserExists(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found")
        }
        userService.updateUser(user)
        return ResponseEntity.status(HttpStatus.OK).body("User Updated Successfully")
    }

    @DeleteMapping("/users/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<String> {
        val isRemovedUser = userService.deleteUser(id)
        if (isRemovedUser) {
            return ResponseEntity.status(HttpStatus.OK).body("User Deleted Successfully")
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found")
    }
}