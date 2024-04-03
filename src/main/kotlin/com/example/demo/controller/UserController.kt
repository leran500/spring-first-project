package com.example.demo.controller

import com.example.demo.dto.CreateUserDto
import com.example.demo.domain.User
import com.example.demo.dto.ListUserDto
import com.example.demo.dto.UpdateUserDto
import com.example.demo.service.UserService
import jakarta.validation.Valid;
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/users")
@Validated
class UserController(val messageService: UserService) {

    @PostMapping
    fun createUser(@Valid @RequestBody createUserDto: CreateUserDto): ResponseEntity<User> {
        createUserDto.email
      return  ResponseEntity.status(HttpStatus.CREATED).body(messageService.createUser(createUserDto))
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: String): User = messageService.getUserById(id)


    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: String, @Valid @RequestBody updateUserDto: UpdateUserDto): ResponseEntity<User> =
        ResponseEntity.status(HttpStatus.OK).body(messageService.updateUser(id, updateUserDto));

    @GetMapping
    fun listMessages(
        @RequestParam firstName: String?,
        @RequestParam lastName: String?,
        @RequestParam email: String?,
        @RequestParam balance: Int?,
        pageable: Pageable,
    ): ResponseEntity<Page<User?>> = ResponseEntity.status(HttpStatus.OK).body( messageService.listUsers(ListUserDto(firstName, lastName, email, balance), pageable))

}