package com.makagonov.orders.autorithation.model

import java.time.LocalDateTime
import jakarta.persistence.*

@Entity
@Table(name = "sessions")
data class Session(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne @JoinColumn(name = "user_id")
    val user: User,
    val token: String,
    val expires: LocalDateTime
)