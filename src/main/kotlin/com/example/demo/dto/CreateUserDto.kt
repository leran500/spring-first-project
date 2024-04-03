package com.example.demo.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull;


data class CreateUserDto(
    @field:NotNull(message = "firstName should not be empty")
    val firstName: String? =null,
    val lastName: String? = null,
    @field:NotNull(message = "email should not be empty")
    @field:Email(message = "Invalid email format")
    val email: String? = null,
    val balance: Int? = null
)


