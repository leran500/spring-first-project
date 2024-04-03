package com.example.demo.dao.models
import jakarta.persistence.*

@Entity
@Table(name = "users") // @entity annotation can be enough => JPA will assume that the table name is Message
 class UserModel(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: String? = null,

    @Column(name = "first_name")
    var firstName: String? = null,

    @Column(name = "last_name")
    var lastName: String? = null,

    @Column(name = "email")
    var email: String? = null,

    @Column(name = "balance")
    var balance: Int? = null
)