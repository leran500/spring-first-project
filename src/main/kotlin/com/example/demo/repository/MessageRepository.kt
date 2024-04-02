package com.example.demo.repository

import com.example.demo.dao.models.MessageModel
import com.example.demo.dto.CreateMessageDto
import com.example.demo.dto.ListMessageDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface MessageRepository {
    fun getById(id :String): MessageModel
    fun list(listMessageDto: ListMessageDto, pageable: Pageable): Page<MessageModel?>
    fun deleteById(id:String):Unit
    fun create(createMessageDto: CreateMessageDto): MessageModel
}