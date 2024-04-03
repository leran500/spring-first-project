package com.example.demo.dao
import com.example.demo.dao.models.UserModel
import org.springframework.data.jpa.repository.JpaRepository


interface UserDAO : JpaRepository<UserModel?, String?> {
}