package com.makagonov.orders.business.model

import jakarta.persistence.*

@Entity
@Table(name = "stations")
data class Station(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val station: String
)