package com.example.demo.controller

import com.example.demo.dto.CreateMessageDto
import com.example.demo.domain.Message
import com.example.demo.dto.ListMessageDto
import com.example.demo.service.MessageService
import jakarta.validation.Valid;
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException


@RestController
@RequestMapping("/api/v1/messages")
@Validated
class MessageController(val messageService: MessageService) {

    @PostMapping
    fun createMessage(@Valid @RequestBody createMessageDto: CreateMessageDto): ResponseEntity<Message> {
        createMessageDto.email
      return  ResponseEntity.status(HttpStatus.CREATED).body(messageService.createMessage(createMessageDto))
    }

    @GetMapping("/{id}")
    fun getMessageById(@PathVariable id: String): Message = messageService.getMessageById(id)


    @DeleteMapping("/{id}")
    fun deleteMessage(@PathVariable id: String): ResponseEntity<Unit> =
        ResponseEntity.status(HttpStatus.OK).body(messageService.deleteMessages(id));

    @GetMapping
    fun listMessages(
        @RequestParam firstName: String?,
        @RequestParam lastName: String?,
        @RequestParam email: String?,
        @RequestParam balance: Int?,
        pageable: Pageable,
    ): ResponseEntity<Page<Message?>> = ResponseEntity.status(HttpStatus.OK).body( messageService.listMessages(ListMessageDto(firstName, lastName, email, balance), pageable))

}