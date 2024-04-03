package com.example.demo.repository

import com.example.demo.dao.models.UserModel
import com.example.demo.dto.CreateUserDto
import com.example.demo.dto.ListUserDto
import com.example.demo.dto.UpdateUserDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface UserRepository {
    fun getById(id :String): UserModel
    fun list(listMessageDto: ListUserDto, pageable: Pageable): Page<UserModel?>
    fun updateUser(id:String, updateUserDto: UpdateUserDto):UserModel
    fun create(createMessageDto: CreateUserDto): UserModel
}