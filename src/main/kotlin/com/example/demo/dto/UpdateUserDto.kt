package com.example.demo.dto

import jakarta.validation.constraints.Email


data class UpdateUserDto(
    val firstName: String? =null,
    val lastName: String? = null,
    @field:Email(message = "Invalid email format")
    val email: String? = null,
    val balance: Int? = null
)


