package com.example.capstone_project.model

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class Order(
    val orderId: Long?,
    val products: MutableList<ProductOrder>?,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    val orderDate: LocalDate,
)
