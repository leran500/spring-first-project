package com.example.demo.dao.models.extensions

import com.example.demo.domain.User
import com.example.demo.dao.models.UserModel


fun UserModel?.toUser():User = User(id = this?.id, firstName =this?.firstName, lastName = this?.lastName, email = this?.email, balance = this?.balance)
