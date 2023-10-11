package com.example.capstone_project.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(private val javaMailSender: JavaMailSender) {
    fun sendRegistrationEmail(email: String) {
        val message = SimpleMailMessage()
        message.setTo(email)
        message.from = "ecomzy@gmail.com"
        message.subject = "Welcome to Our Application"
        message.text = "Thank you for registering on our platform!"
        javaMailSender.send(message)
    }
}
