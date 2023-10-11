package com.example.capstone_project.model

import java.util.*

data class Order(val orderId:Long?,val products: MutableList<Product>?, val orderDate: String?, val isDelivered: Boolean)