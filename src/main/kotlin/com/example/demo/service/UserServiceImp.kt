package com.example.demo.service

import com.example.demo.dto.CreateUserDto
import com.example.demo.dto.ListUserDto
import com.example.demo.dao.models.extensions.toUser
import com.example.demo.domain.*
import com.example.demo.dto.UpdateUserDto
import com.example.demo.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service



@Service
class UserServiceImp(
    private val userRepository: UserRepository,
) : UserService {
    override fun getUserById(id: String): User = userRepository.getById(id).toUser();
    override fun updateUser(id: String, updateUserDto: UpdateUserDto): User = userRepository.updateUser(id, updateUserDto).toUser();
    override fun createUser(createMessageDto: CreateUserDto): User = userRepository.create(
        createMessageDto
    ).toUser();

    override fun listUsers(listUserDto: ListUserDto, pageable: Pageable): Page<User?> =
        userRepository.list(listUserDto, pageable).map { userModel -> userModel.toUser() }
}
