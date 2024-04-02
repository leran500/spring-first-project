package com.example.demo.domain

import jakarta.persistence.*


  data class Message(

   var id: String?,

   var firstName: String?,

   var lastName: String?,

   var email: String?,

   var balance: Int?
 )