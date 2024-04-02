package com.example.demo.dao
import com.example.demo.dao.models.MessageModel
import org.springframework.data.jpa.repository.JpaRepository


interface MessageDAO : JpaRepository<MessageModel?, String?> {
}