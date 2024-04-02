package com.example.demo.dao.models.extensions

import com.example.demo.domain.Message
import com.example.demo.dao.models.MessageModel


fun MessageModel?.toMessage():Message = Message(id = this?.id, firstName =this?.firstName, lastName = this?.lastName, email = this?.email, balance = this?.balance)
