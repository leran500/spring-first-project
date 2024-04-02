package com.example.demo.service

import com.example.demo.domain.Message
import com.example.demo.dto.CreateMessageDto
import com.example.demo.dto.ListMessageDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface MessageService{
    fun getMessageById(id :String): Message
    fun listMessages(listMessageDto: ListMessageDto ,pageable: Pageable): Page<Message?>
    fun deleteMessages(id:String):Unit
    fun createMessage(createMessageDto: CreateMessageDto): Message
}