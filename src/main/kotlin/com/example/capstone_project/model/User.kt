package com.example.capstone_project.model

data class User(
    val id: Long?, val username: String?, val address: Address, val orders: MutableList<Order>?, val wishList: List<Product>?
)