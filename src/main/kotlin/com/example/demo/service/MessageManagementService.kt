package com.example.demo.service

import com.example.demo.dto.CreateMessageDto
import com.example.demo.dto.ListMessageDto
import com.example.demo.dao.models.extensions.toMessage
import com.example.demo.domain.*
import com.example.demo.repository.MessageRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service



@Service
class MessageManagementService(
    private val messageRepository: MessageRepository,
) : MessageService {


    override fun getMessageById(id: String): Message = messageRepository.getById(id).toMessage();
    override fun deleteMessages(id: String): Unit = messageRepository.deleteById(id);
    override fun createMessage(createMessageDto: CreateMessageDto): Message = messageRepository.create(
        createMessageDto
    ).toMessage();

    override fun listMessages(listMessageDto: ListMessageDto, pageable: Pageable): Page<Message?> =
        messageRepository.list(listMessageDto, pageable).map { messageModel -> messageModel.toMessage() }
}
