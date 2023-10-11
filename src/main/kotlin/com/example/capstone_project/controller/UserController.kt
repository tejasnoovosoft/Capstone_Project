package com.example.capstone_project.controller

import com.example.capstone_project.model.User
import com.example.capstone_project.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class UserController {
    @Autowired
    private lateinit var userService: UserService

    @PostMapping("/user/create")
    fun createNewUser(@RequestBody user: User): ResponseEntity<String> {
        if (user.id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid User")
        }
        if (userService.isUserExists(user.id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User Already Exists")
        }
        userService.addUser(user)
        return ResponseEntity.status(HttpStatus.CREATED).body("User Successfully Created")
    }

    @GetMapping("/users")
    fun getAllUsers(): ResponseEntity<Set<User>> {
        val users = userService.getAllUsers()
        return ResponseEntity.of(users)
    }

    @GetMapping("/user/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<User> {
        val user = userService.getUserById(id)
        return ResponseEntity.of(user)
    }

    @PutMapping("/user/update/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody user: User): ResponseEntity<String> {
        if (!userService.isUserExists(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found")
        }
        userService.updateUser(user)
        return ResponseEntity.status(HttpStatus.OK).body("User Updated Successfully")
    }

    @DeleteMapping("/user/delete/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<String> {
        if (!userService.isUserExists(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found")
        }
        userService.deleteUser(id)
        return ResponseEntity.status(HttpStatus.OK).body("User Deleted Successfully")
    }
}