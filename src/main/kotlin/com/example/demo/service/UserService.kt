package com.example.demo.service

import com.example.demo.domain.User
import com.example.demo.dto.CreateUserDto
import com.example.demo.dto.ListUserDto
import com.example.demo.dto.UpdateUserDto
import org.apache.logging.log4j.message.Message
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface UserService{
    fun getUserById(id :String): User
    fun listUsers(listUserDto: ListUserDto, pageable: Pageable): Page<User?>
    fun updateUser(id:String, updateUserDto: UpdateUserDto):User
    fun createUser(createUserDto: CreateUserDto): User
}