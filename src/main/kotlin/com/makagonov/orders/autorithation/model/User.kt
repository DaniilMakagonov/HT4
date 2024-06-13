package com.makagonov.orders.autorithation.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
    val userName: String,
    @Column(unique = true)
    val email: String,
    val password: String,
    val createdTime: LocalDateTime = LocalDateTime.now()
)