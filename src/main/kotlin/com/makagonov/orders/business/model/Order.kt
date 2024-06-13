package com.makagonov.orders.business.model

import com.makagonov.orders.autorithation.model.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
data class Order(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne @JoinColumn(name = "user_id")
    val user: User,
    @ManyToOne @JoinColumn(name = "from_station_id")
    val fromStation: Station,
    @ManyToOne @JoinColumn(name = "to_station_id")
    val toStation: Station,
    var status: Int,
    val created: LocalDateTime = LocalDateTime.now()
)