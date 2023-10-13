package com.example.capstone_project.model

data class User(
    val userId: Long?,
    val username: String?,
    val email: String?,
    val address: Address,
    val orders : MutableList<Order>?,
    val wishList: List<Product>?
)